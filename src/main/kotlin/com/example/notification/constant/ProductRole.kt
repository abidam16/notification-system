package com.example.notification.constant

enum class ProductRole(
    val roleString: String,
    val roleInt: Int,
    val label: String
) {
    OWNER("OWNER", 0, "Owner"),
    VIEWER("VIEWER", 1, "Viewer"),
    EDITOR("EDITOR", 2, "Editor"),
}