package com.example.notification.controller

import com.example.notification.dto.ReadNotificationRequestDto
import com.example.notification.entity.Notification
import com.example.notification.service.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/notfication")
class NotificationController(
    private val notificationService: NotificationService
) {
    @GetMapping
    fun getNotifications(@RequestBody readNotificationRequestDto: ReadNotificationRequestDto): List<Notification> {
        return notificationService.getNotificationWithPagination(readNotificationRequestDto)
    }
}