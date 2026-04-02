package com.example.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,
)