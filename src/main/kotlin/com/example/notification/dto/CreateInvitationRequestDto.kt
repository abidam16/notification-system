package com.example.notification.dto

import com.example.notification.constant.ProductInvitationStatus
import com.example.notification.entity.Notification
import com.example.notification.entity.ProductInvitation

data class CreateInvitationRequestDto (
    val productId: Long,
    val inviterUserId: Long,
    val targetUserId: Long,
    val requestedRole: Int,
    val status: Int?
)

data class UpdateInvitationRequestDto (
    val id: Long,
    val requestedRole: Int,
    val status: Int,
    var notificationMessage: String?,
    var notificationId: Long?,
)

data class ProductAndInviterName(
    val productName: String,
    val inviterName: String,
)

fun CreateInvitationRequestDto.toEntity(): ProductInvitation =
    ProductInvitation(
        productId = this.productId,
        inviterUsersId = this.inviterUserId,
        targetUsersId = this.targetUserId,
        requestedRole = this.requestedRole.toShort(),
        status = ProductInvitationStatus.PENDING.statusInt.toShort()
    )

fun UpdateInvitationRequestDto.toEntity(): ProductInvitation =
    ProductInvitation(
        id = this.id,
        requestedRole = this.requestedRole.toShort(),
        status = this.status.toShort()
    )

fun UpdateInvitationRequestDto.toNotification(): Notification =
    Notification(
        id = this.notificationId,
        body = this.notificationMessage
    )