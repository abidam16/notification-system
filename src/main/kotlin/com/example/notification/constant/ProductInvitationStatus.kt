package com.example.notification.constant

enum class ProductInvitationStatus(
    val statusString: String,
    val statusInt: Int,
    val label: String
) {
    PENDING("PENDING", 0, "Pending"),
    ACCEPTED("ACCEPTED", 1, "Accepted"),
    REJECTED("REJECTED", 2, "Rejected"),
}