package com.example.notification.dto

import com.example.notification.entity.Notification
import jakarta.persistence.Column

data class NotificationRequestDto (
    var userId: Long?,
    var type: String,
    var referenceId: Long?,
    var title: String?,
    var body: String?,
    var status: String?,
)

data class ReadNotificationRequestDto (
    var id: Long?,
    var status: String?,
)

data class UpdateStatusNotificationDto (
    var id: Long?,
    var status: String?,
    var body: String?,
)

fun NotificationRequestDto.toEntity(): Notification =
    Notification(
        userId = this.userId,
        type = this.type,
        referenceId = this.referenceId,
        title = this.title,
        body = this.body,
        status = "UNREAD",
    )

fun ReadNotificationRequestDto.toEntity(): Notification =
    Notification(
        id = this.id,
        status = "READ",
    )

fun UpdateStatusNotificationDto.toEntity(): Notification =
    Notification(
        id = this.id,
        status = this.status,
        body = this.body,
    )