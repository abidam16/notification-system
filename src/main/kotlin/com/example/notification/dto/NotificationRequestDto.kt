package com.example.notification.dto

import com.example.notification.constant.NotificationStatus
import com.example.notification.entity.Notification
import jakarta.persistence.Column

data class NotificationRequestDto (
    var userId: Long?,
    var fromUserId: Long?,
    var type: Int,
    var referenceId: Long?,
    var title: String?,
    var body: String?,
    var status: Int?,
)

data class ReadNotificationRequestDto (
    var id: Long?,
    var status: Int?,
)

data class UpdateStatusNotificationDto (
    var id: Long?,
    var status: Int?,
    var body: String?,
)