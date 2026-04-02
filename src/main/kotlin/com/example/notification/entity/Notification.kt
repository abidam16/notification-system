package com.example.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "notification")
class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_id")
    var usersId: Long? = null,

    @Column(name = "from_user_id")
    var fromUsersId: Long? = null,

    @Column(name = "type")
    var type: Short? = null,

    @Column(name = "reference_id")
    var referenceId: Long? = null,

    @Column(name = "title")
    var title: String? = null,

    @Column(name = "body")
    var body: String? = null,

    @Column(name = "status")
    var status: Short? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "read_at", updatable = false)
    var readAt: LocalDateTime? = null,

    )