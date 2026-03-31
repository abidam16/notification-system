package com.example.notification.dto

import com.example.notification.entity.ProductInvitation
import java.time.LocalDateTime

data class CreateInvitationResponseDto (
    val productId: Long,
    val inviterUserId: Long,
    val targetUserId: Long,
    val requestedRole: String,
    val status: String,
    val message: String,
    val createdAt: LocalDateTime?,
    val expiredAt: LocalDateTime?,
)

data class UpdateInvitationResponseDto (
    val productId: Long,
    val inviterUserId: Long,
    val targetUserId: Long,
    val requestedRole: String,
    val status: String,
    val message: String,
    val updatedAt: LocalDateTime?,
)

fun CreateInvitationResponseDto.toEntity(): ProductInvitation =
    ProductInvitation(
        productId = this.productId,
        inviterUserId = this.inviterUserId,
        targetUserId = this.targetUserId,
        requestedRole = this.requestedRole,
        status = "PENDING",
        message = this.message,
        createdAt = this.createdAt,
        expiresAt = this.expiredAt,
    )

fun UpdateInvitationResponseDto.toEntity(): ProductInvitation =
    ProductInvitation(
        productId = this.productId,
        inviterUserId = this.inviterUserId,
        targetUserId = this.targetUserId,
        requestedRole = this.requestedRole,
        status = this.status,
        message = this.message,
        updatedAt = this.updatedAt,
    )