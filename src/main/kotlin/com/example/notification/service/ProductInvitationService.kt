package com.example.notification.service

import com.example.notification.constant.NotificationStatus
import com.example.notification.constant.NotificationType
import com.example.notification.constant.ProductInvitationStatus
import com.example.notification.constant.ProductRole
import com.example.notification.dto.CreateInvitationRequestDto
import com.example.notification.dto.UpdateInvitationRequestDto
import com.example.notification.dto.toEntity
import com.example.notification.dto.toNotification
import com.example.notification.entity.Notification
import com.example.notification.entity.ProductInvitation
import com.example.notification.repository.NotificationRepo
import com.example.notification.repository.ProductInvitationRepo
import com.example.notification.repository.ProductRepo
import com.example.notification.repository.UserRepo
import com.example.notification.utils.NotificationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductInvitationService (
    private val productInvitationRepo: ProductInvitationRepo,
    private val notificationRepo: NotificationRepo,
    private val productRepo: ProductRepo,
    private val userRepo: UserRepo
) {
    fun getProductInvitation(productId: Long): ProductInvitation? {
        return productInvitationRepo.getProductInvitationById(productId)
    }

    fun getAllProductInvitations(statusInt: Int): List<ProductInvitation> {
        return productInvitationRepo.getProductInvitationsByStatusEquals(statusInt)
    }

    @Transactional
    fun createProductInvitation(createInvitationRequestDto: CreateInvitationRequestDto) {
        val currentTargetUser = userRepo.findUserById(createInvitationRequestDto.targetUserId)
        val currentInviterUser = userRepo.findUserById(createInvitationRequestDto.inviterUserId)
        val currentProduct = productRepo.findProductById(createInvitationRequestDto.productId)
        if (currentTargetUser == null) {
            throw IllegalArgumentException("Invitation with ID ${createInvitationRequestDto.targetUserId} not found")
        }

        if (currentInviterUser == null) {
            throw IllegalArgumentException("Notification with ID ${createInvitationRequestDto.inviterUserId} not found")
        }

        if (currentProduct == null) {
            throw IllegalArgumentException("Invitation with ID ${createInvitationRequestDto.productId} not found")
        }

        val currentInvitation = productInvitationRepo
            .getExistProductInvitations(
                createInvitationRequestDto.inviterUserId,
                createInvitationRequestDto.targetUserId,
                createInvitationRequestDto.productId,
                listOf(ProductInvitationStatus.PENDING.statusInt, ProductInvitationStatus.ACCEPTED.statusInt)
            )

        if (currentInvitation != null) {
            if (currentInvitation.status == ProductInvitationStatus.ACCEPTED.statusInt.toShort()) {
                throw IllegalArgumentException("Target user already accepted the invitation.")
            } else {
                throw IllegalArgumentException("Target user is being invited to this product.")
            }
        }

        val currentProductInvitation = productInvitationRepo.save(createInvitationRequestDto.toEntity())

        notificationRepo.save(
            Notification(
                usersId = currentTargetUser.id,
                fromUsersId = currentInviterUser.id,
                type = NotificationType.PRODUCT_INVITATION.typeInt.toShort(),
                referenceId = currentProductInvitation.id,
                title = NotificationType.PRODUCT_INVITATION.label,
                body = NotificationUtils.generateMessage(
                    currentProductInvitation.status!!.toInt(),
                    currentInviterUser.name!!,
                    currentProduct.name!!,
                    ProductRole.fromInt(createInvitationRequestDto.requestedRole)!!.label,
                ),
                status = NotificationStatus.UNREAD.statusInt.toShort(),
            )
        )
    }

    @Transactional
    fun updateProductInvitation(updateInvitationRequestDto: UpdateInvitationRequestDto) {
        val currentInvitation = productInvitationRepo.findProductInvitationByIdAndStatus(
            updateInvitationRequestDto.id, ProductInvitationStatus.PENDING.statusInt)
        val currentNotification = notificationRepo.findNotificationByReferenceId(updateInvitationRequestDto.id)
        if (currentInvitation == null) {
            throw IllegalArgumentException("Invitation with ID ${updateInvitationRequestDto.id} not found")
        }

        if (currentNotification == null) {
            throw IllegalArgumentException("Notification with ID ${updateInvitationRequestDto.id} not found")
        }

        val currentUser = userRepo.findUserById(currentInvitation.inviterUsersId!!)
        val currentProduct = productRepo.findProductById(currentInvitation.productId!!)

        currentInvitation.apply {
            requestedRole = updateInvitationRequestDto.requestedRole.toShort()
            status = updateInvitationRequestDto.status.toShort()
        }

        currentNotification.apply {
            body = NotificationUtils.generateMessage(
                updateInvitationRequestDto.status,
                currentUser!!.name!!,
                currentProduct!!.name!!,
                ProductRole.fromInt(updateInvitationRequestDto.requestedRole)!!.label,
            )
        }


        productInvitationRepo.save(currentInvitation)
        notificationRepo.save(currentNotification)
    }

    @Transactional
    fun cancelProductInvitation(productInvitationId: Long) {
        val currentInvitation = productInvitationRepo.findProductInvitationByIdAndStatus(productInvitationId,
            ProductInvitationStatus.PENDING.statusInt)
        val currentNotification = notificationRepo.findNotificationByReferenceId(productInvitationId)
        if (currentInvitation == null) {
            throw IllegalArgumentException("Invitation with ID $productInvitationId not found")
        }
        if (currentNotification == null) {
            throw IllegalArgumentException("Notification with invitation ID $productInvitationId not found")
        }

        val currentInviterUser = userRepo.findUserById(currentInvitation.inviterUsersId!!)
        val currentProduct = productRepo.findProductById(productInvitationId)

        if (currentInviterUser == null) {
            throw IllegalArgumentException("Inviter user with ID $productInvitationId not found")
        }
        if (currentProduct == null) {
            throw IllegalArgumentException("Product with ID $productInvitationId not found")
        }

        currentNotification.apply {
            body = NotificationUtils.generateMessage(
                ProductInvitationStatus.CANCELLED.statusInt,
                currentInviterUser.name!!,
                currentProduct.name!!,
                ProductRole.fromInt(currentInvitation.requestedRole!!.toInt())!!.label,
            )
        }

        notificationRepo.save(currentNotification)
        productInvitationRepo.deleteById(productInvitationId)
    }
}