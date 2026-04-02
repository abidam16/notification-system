package com.example.notification.controller

import com.example.notification.dto.CreateInvitationRequestDto
import com.example.notification.dto.UpdateInvitationRequestDto
import com.example.notification.entity.ProductInvitation
import com.example.notification.service.ProductInvitationService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/invitation")
class ProductInvitationController(
    private val productInvitationService: ProductInvitationService,
) {

    @GetMapping
    fun productInvitations(@RequestParam status: Int): List<ProductInvitation> {
        return productInvitationService.getAllProductInvitations(status)
    }

    @GetMapping("/{productInvitationId}")
    fun productInvitationBy(@PathVariable productInvitationId: Long): ProductInvitation? {
        return productInvitationService.getProductInvitation(productInvitationId)
    }

    @PostMapping
    fun createProductInvitation(@RequestBody createInvitationRequestDto: CreateInvitationRequestDto) {
        productInvitationService.createProductInvitation(createInvitationRequestDto)
    }

    @PutMapping
    fun updateProductInvitation(@RequestBody updateInvitationRequestDto: UpdateInvitationRequestDto) {
        productInvitationService.updateProductInvitation(updateInvitationRequestDto)
    }

    @DeleteMapping
    fun cancelProductInvitation(@RequestParam id: Long) {
        productInvitationService.cancelProductInvitation(id)
    }
}