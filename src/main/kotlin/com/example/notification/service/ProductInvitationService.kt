package com.example.notification.service

import com.example.notification.constant.ProductInvitationStatus
import com.example.notification.dto.CreateInvitationRequestDto
import com.example.notification.dto.UpdateInvitationRequestDto
import com.example.notification.dto.toEntity
import com.example.notification.dto.toNotification
import com.example.notification.entity.ProductInvitation
import com.example.notification.repository.NotificationRepo
import com.example.notification.repository.ProductInvitationRepo
import com.example.notification.repository.ProductRepo
import com.example.notification.repository.UserRepo
import com.example.notification.utils.NotificationUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class ProductInvitationService (
    private val productInvitationRepo: ProductInvitationRepo,
    private val notificationRepo: NotificationRepo,
    private val productRepo: ProductRepo,
    private val userRepo: UserRepo
) {
    fun getProductInvitation(productId: Long): Optional<ProductInvitation?> {
        return productInvitationRepo.findById(productId)
    }

    fun getAllProductInvitations(): List<ProductInvitation> {
        return productInvitationRepo.findAll()
    }

    fun createProductInvitation(createInvitationRequestDto: CreateInvitationRequestDto) {
//        productInvitationRepo.save(createInvitationRequestDto.toEntity())
    }

    @Transactional
    fun updateProductInvitation(invitationId: Long, updateInvitationRequestDto: UpdateInvitationRequestDto) {
        val currentInvitation = productInvitationRepo.findProductInvitationByIdAndStatus(invitationId, "PENDING")
        val currentNotification = notificationRepo.findNotificationByReferenceId(invitationId)
        if (currentInvitation == null) {
            throw IllegalArgumentException("Invitation with ID $invitationId not found")
        }

        if (currentNotification == null) {
            throw IllegalArgumentException("Notification with ID $invitationId not found")
        }

        val currentUser = userRepo.findUserById(currentInvitation.inviterUserId!!.id!!)
        val currentProduct = productRepo.findProductById(currentInvitation.productId!!.id!!)

        updateInvitationRequestDto.apply {
            notificationMessage = NotificationUtils.generateMessage(
                updateInvitationRequestDto.status,
                currentUser!!.name!!,
                currentProduct!!.name!!,
                updateInvitationRequestDto.requestedRole,
            )
        }


        productInvitationRepo.save(updateInvitationRequestDto.toEntity())
        notificationRepo.save(updateInvitationRequestDto.toNotification())
    }

    fun deleteProductInvitation(productId: Long) {
        productInvitationRepo.deleteById(productId)
    }
}