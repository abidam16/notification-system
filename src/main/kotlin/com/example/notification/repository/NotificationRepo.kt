package com.example.notification.repository

import com.example.notification.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepo : JpaRepository<Notification, Long> {
    fun findNotificationByReferenceId(referenceId: Long): Notification?
}