package com.example.notification.dto

import java.time.LocalDateTime

data class NotificationResponseDto (
    var id: Long? = null,
    var status: String? = null,
    var type: String? = null,
    var createdAt: LocalDateTime? = null,
    var actionable: Boolean? = null,
    var title: String? = null,
    var body: String? = null,
    var actions: Boolean? = null,
    var actionButtons: List<ButtonNotification>? = null,
    var badge: Badge? = null,
    var fromId: Long? = null,
    var fromName: String? = null,
    var productId: String? = null,
)

data class ButtonNotification(
    var type: String,
    var label: String,
    var color: String,
)

data class Badge(
    var text: String,
    var color: String,
)