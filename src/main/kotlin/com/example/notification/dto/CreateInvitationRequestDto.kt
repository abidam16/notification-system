package com.example.notification.dto

import com.example.notification.entity.Notification
import com.example.notification.entity.Product
import com.example.notification.entity.ProductInvitation
import com.example.notification.entity.User

data class CreateInvitationRequestDto (
    val productId: Long,
    val inviterUserId: Long,
    val targetUserId: Long,
    val requestedRole: String,
    val status: String,
    val productName: String,
    val inviterName: String,
    val notificationMessage: String
)

data class UpdateInvitationRequestDto (
    val id: Long,
    val requestedRole: String,
    val status: String,
    var notificationMessage: String,
    val notificationId: Long,
)

data class ProductAndInviterName(
    val productName: String,
    val inviterName: String,
)

fun CreateInvitationRequestDto.toEntity(
    product: Product,
    user: User
): ProductInvitation =
    ProductInvitation(
        productId = product,
        inviterUserId = user,
        targetUserId = user,
        requestedRole = this.requestedRole,
        status = "PENDING"
    )

fun UpdateInvitationRequestDto.toEntity(): ProductInvitation =
    ProductInvitation(
        id = this.id,
        requestedRole = this.requestedRole,
        status = this.status
    )

fun UpdateInvitationRequestDto.toNotification(): Notification =
    Notification(
        id = this.notificationId,
        body = this.notificationMessage
    )