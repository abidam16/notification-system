package com.example.notification.repository

import com.example.notification.entity.ProductInvitation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductInvitationRepo : JpaRepository<ProductInvitation, Long> {
    fun findProductInvitationByIdAndStatus(productId: Long, status: String): ProductInvitation?

    fun getProductAndUserDetailByNotificationId(notificationId: Long)

    @Query("""
        SELECT new com.example.notification.dto.ProductAndInviterName(
        inviter.name,
        product.name
        )
        FROM ProductInvitation pi
        JOIN pi.inviterUserId inviter
        JOIN pi.productId product
        WHERE pi.id = :userId
    """)
    fun getProductAndUserDetailByProductId(productId: Long)
}