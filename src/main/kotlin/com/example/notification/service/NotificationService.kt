package com.example.notification.service

import com.example.notification.repository.NotificationRepo
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepo: NotificationRepo
) {
    fun getNotificationWithPagination(notificationId: Long) {
        notificationRepo.findNotificationByReferenceId(notificationId)
    }
}