package com.example.notification.utils

import com.example.notification.constant.ProductInvitationStatus
import com.example.notification.dto.UpdateInvitationRequestDto

object NotificationUtils {
    fun generateMessage(status: String, inviterName: String, productName: String, requestedRole: String): String {
        return when (status) {
            ProductInvitationStatus.ACCEPTED.statusString -> "You accepted the invitation"
            ProductInvitationStatus.REJECTED.statusString -> "You rejected the invitation"
            else -> "$inviterName invited you as $requestedRole to $productName"
        }
    }
}