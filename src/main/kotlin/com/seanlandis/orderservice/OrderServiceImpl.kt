package com.seanlandis.orderservice

import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val productsRepository: ProductRepository) : OrderService {
    override fun calculateSubtotal(productNames: List<String>): CurrencyAmount {
        return createOrder(productNames).calculateSubtotal()
    }

    private fun createOrder(productNames: List<String>): Order {
        val products = productNames.asSequence().mapNotNull { name -> productsRepository.getProductByName(name) }.toList()
        return Order(products)
    }
}