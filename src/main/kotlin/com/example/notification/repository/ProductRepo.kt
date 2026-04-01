package com.example.notification.repository

import com.example.notification.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepo : JpaRepository<Product, Long> {
    fun findProductById(id: Long): Product?
}