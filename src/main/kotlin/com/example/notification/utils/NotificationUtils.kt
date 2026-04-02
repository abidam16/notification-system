package com.example.notification.utils

import com.example.notification.constant.ProductInvitationStatus
import com.example.notification.dto.UpdateInvitationRequestDto

object NotificationUtils {
    fun generateMessage(status: Int, inviterName: String, productName: String, requestedRole: String): String {
        return when (status) {
            ProductInvitationStatus.ACCEPTED.statusInt -> "You accepted the invitation"
            ProductInvitationStatus.REJECTED.statusInt -> "You rejected the invitation"
            ProductInvitationStatus.CANCELLED.statusInt -> "The invitation is cancelled"
            else -> "$inviterName invited you as $requestedRole to $productName"
        }
    }
}