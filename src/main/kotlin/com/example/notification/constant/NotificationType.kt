package com.example.notification.constant

enum class NotificationType(
    val typeString: String,
    val typeInt: Int,
    val label: String,
) {
    NOTIFICATION("NOTIFICATION", 1, "Notification"),
    COMMENT("COMMENT", 2, "Comment"),
    MENTION("MENTION", 3, "Mention"),
}