package com.example.notification.service

import com.example.notification.dto.CreateInvitationRequestDto
import com.example.notification.dto.UpdateInvitationRequestDto
import com.example.notification.dto.toEntity
import com.example.notification.entity.ProductInvitation
import com.example.notification.repository.ProductInvitationRepo
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductInvitationService (
    private val productInvitationRepo: ProductInvitationRepo
) {
    fun getProductInvitation(productId: Long): Optional<ProductInvitation?> {
        return productInvitationRepo.findById(productId)
    }

    fun getAllProductInvitations(): List<ProductInvitation> {
        return productInvitationRepo.findAll()
    }

    fun createProductInvitation(createInvitationRequestDto: CreateInvitationRequestDto) {
        productInvitationRepo.save(createInvitationRequestDto.toEntity())
    }

    fun updateProductInvitation(updateInvitationRequestDto: UpdateInvitationRequestDto) {
        productInvitationRepo.save(updateInvitationRequestDto.toEntity())
    }

    fun deleteProductInvitation(productId: Long) {
        productInvitationRepo.deleteById(productId)
    }
}