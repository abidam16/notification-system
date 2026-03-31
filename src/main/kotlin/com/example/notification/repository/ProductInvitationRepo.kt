package com.example.notification.repository

import com.example.notification.entity.ProductInvitation
import org.springframework.data.jpa.repository.JpaRepository

interface ProductInvitationRepo : JpaRepository<ProductInvitation, Long>