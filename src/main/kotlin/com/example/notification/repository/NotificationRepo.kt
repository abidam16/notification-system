package com.example.notification.repository

import com.example.notification.entity.Notification
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface NotificationRepo : JpaRepository<Notification, Long> {
    fun findNotificationByUsersId(referenceId: Long): Notification?

    @Query(value = """
        SELECT *
        FROM Notification n
        WHERE
        user_id = :userId
        AND (
            :cursorCreatedAt IS NULL
            OR n.createdAt < :cursorCreatedAt
            OR (n.createdAt = :cursorCreatedAt AND n.id < :cursorId)
        )
        ORDER BY n.createdAt DESC, n.id DESC
    """,
        nativeQuery = true)
    fun findNextPageByCreatedAtDesc(
        @Param("userId") userId: Long,
        @Param("cursorCreatedAt") cursorCreatedAt: LocalDateTime?,
        @Param("cursorId") cursorId: Long?,
        pageable: Pageable
    ): List<Notification>
}