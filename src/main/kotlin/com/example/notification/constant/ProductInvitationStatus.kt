package com.example.notification.constant

enum class ProductInvitationStatus(
    val statusString: String,
    val statusInt: Int,
    val label: String
) {
    PENDING("PENDING", 0, "Pending"),
    ACCEPTED("ACCEPTED", 1, "Accepted"),
    REJECTED("REJECTED", 2, "Rejected"),
    CANCELLED("CANCELLED", 3, "Canceled");

    companion object {
        private val mapInt = ProductInvitationStatus.entries.associateBy(ProductInvitationStatus::statusInt)

        fun fromInt(statusInt: Int) = mapInt[statusInt]
    }
}