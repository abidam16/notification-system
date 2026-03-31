package com.example.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "notification")
class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "user_id")
    var userId: Long?,

    @Column(name = "type")
    var type: String,

    @Column(name = "reference_id")
    var referenceId: Long?,

    @Column(name = "title")
    var title: String?,

    @Column(name = "body")
    var body: String?,

    @Column(name = "status")
    var status: String?,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime?,

    @Column(name = "read_at", updatable = false)
    var readAt: LocalDateTime?,

) {
    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        readAt = LocalDateTime.now()
    }
}