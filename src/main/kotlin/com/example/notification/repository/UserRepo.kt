package com.example.notification.repository

import com.example.notification.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<Users, Long> {
    fun findUserById(id: Long): Users?
}