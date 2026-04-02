package com.example.notification.repository

import com.example.notification.entity.Product
import com.example.notification.entity.ProductInvitation
import com.example.notification.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductInvitationRepo : JpaRepository<ProductInvitation, Long> {
    fun findProductInvitationByIdAndStatus(productId: Long, status: Int): ProductInvitation?

    fun getProductInvitationsByStatusEquals(status: Int): List<ProductInvitation>

    fun getProductInvitationById(userId: Long): ProductInvitation?

    @Query(
        value = """
            SELECT *
            FROM product_invitation
            WHERE inviter_user_id = :inviterUsersId
            AND target_user_id = :targetUsersId
            AND product_id = :productId
            AND status IN (:statuses)
        """,
        nativeQuery = true
    )
    fun getExistProductInvitations(
        inviterUsersId: Long,
        targetUsersId: Long,
        productId: Long,
        statuses: List<Int>
    ): ProductInvitation?

    @Query("""
        SELECT new com.example.notification.dto.ProductAndInviterName(
        u.name,
        p.name
        )
        FROM ProductInvitation pi
        JOIN Users u ON pi.inviterUsersId = u.id
        JOIN Product p ON pi.productId = p.id
        WHERE pi.id = :usersId
    """)
    fun getProductAndUserDetailByProductId(productId: Long)
}