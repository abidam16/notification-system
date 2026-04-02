package com.example.notification.constant

enum class ProductRole(
    val roleString: String,
    val roleInt: Int,
    val label: String
) {
    OWNER("OWNER", 0, "Owner"),
    VIEWER("VIEWER", 1, "Viewer"),
    EDITOR("EDITOR", 2, "Editor");

    companion object {
        private val mapInt = entries.associateBy(ProductRole::roleInt)
        private val mapLabel = entries.associateBy(ProductRole::label)

        fun fromInt(statusInt: Int) = mapInt[statusInt]
        fun fromInt(label: String) = mapLabel[label]
    }
}