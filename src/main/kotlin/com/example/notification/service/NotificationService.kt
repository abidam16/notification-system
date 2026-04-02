package com.example.notification.service

import com.example.notification.dto.ReadNotificationRequestDto
import com.example.notification.entity.Notification
import com.example.notification.repository.NotificationRepo
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NotificationService(
    private val notificationRepo: NotificationRepo
) {
    fun getNotificationWithPagination(readNotificationRequestDto: ReadNotificationRequestDto): List<Notification> {
        return notificationRepo.findNextPageByCreatedAtDesc(
            readNotificationRequestDto.id!!,
            readNotificationRequestDto.cursorCreatedAt,
            readNotificationRequestDto.cursorId,
            PageRequest.of(0, readNotificationRequestDto.size!! + 1))
    }
}