package com.example.notification.dto

import com.example.notification.entity.ProductInvitation

data class CreateInvitationRequestDto (
    val productId: Long,
    val inviterUserId: Long,
    val targetUserId: Long,
    val requestedRole: String,
    val status: String,
    val message: String
)

class UpdateInvitationRequestDto (
    val id: Long,
    val requestedRole: String,
    val status: String,
    val message: String
)

fun CreateInvitationRequestDto.toEntity(): ProductInvitation =
    ProductInvitation(
        productId = this.productId,
        inviterUserId = this.inviterUserId,
        targetUserId = this.targetUserId,
        requestedRole = this.requestedRole,
        status = "PENDING",
        message = this.message
    )

fun UpdateInvitationRequestDto.toEntity(): ProductInvitation =
    ProductInvitation(
        id = this.id,
        requestedRole = this.requestedRole,
        status = this.status,
        message = this.message
    )