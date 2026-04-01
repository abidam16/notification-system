package com.example.notification.repository

import com.example.notification.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepo : JpaRepository<User, Long> {
    fun findUserById(id: Long): User?
}