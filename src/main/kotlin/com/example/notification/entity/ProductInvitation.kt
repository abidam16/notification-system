package com.example.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import jakarta.persistence.Version
import lombok.Data
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_invitation")
class ProductInvitation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "product_id")
    var productId: Product? = null,

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "inviter_user_id")
    var inviterUserId: User? = null,

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "target_user_id")
    var targetUserId: User? = null,

    @Column(name = "requested_role")
    var requestedRole: String? = null,

    @Column(name = "status")
    var status: String? = null,

    @Column(name = "message")
    var message: String? = null,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "responded_at")
    var respondedAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "expires_at", updatable = false)
    var expiresAt: LocalDateTime? = null,

    @Version
    @Column(name = "version")
    var version: Int = 0,
) {
    @PrePersist
    fun onCreate() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
        expiresAt = now.plusMonths(1)
    }
}