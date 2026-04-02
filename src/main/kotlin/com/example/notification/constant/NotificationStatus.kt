package com.example.notification.constant

enum class NotificationStatus(
    val statusString: String,
    val statusInt: Int,
    val label: String
) {
    UNREAD("UNREAD", 0, "Unread"),
    READ("READ", 1, "Read");

    companion object {
        private val mapInt = entries.associateBy(NotificationStatus::statusInt)

        fun fromInt(statusInt: Int) = mapInt[statusInt]
    }
}