package com.example.notification.constant

enum class NotificationType(
    val typeString: String,
    val typeInt: Int,
    val label: String,
) {
    PRODUCT_INVITATION("PRODUCT_INVITATION", 1, "Product Invitation"),
    COMMENT("COMMENT", 2, "Comment"),
    MENTION("MENTION", 3, "Mention");

    companion object {
        private val mapInt = entries.associateBy(NotificationType::typeInt)

        fun fromInt(statusInt: Int) = mapInt[statusInt]
    }
}