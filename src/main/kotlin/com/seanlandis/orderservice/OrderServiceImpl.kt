package com.seanlandis.orderservice

import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val orderPublisher: OrderPublisher, private val productsRepository: ProductRepository, private val discounts: List<Discount>) : OrderService {
    override fun calculateSubtotal(productNames: List<String>): CurrencyAmount {
        return createOrder(productNames, emptyList()).calculateSubtotal()
    }

    override fun calculateSubtotalWithDiscounts(productNames: List<String>): CurrencyAmount {
        return createOrder(productNames, discounts).calculateSubtotal()
    }

    override fun submitOrder(productNames: List<String>) {
        createOrder(productNames, discounts).calculateSubtotal()
        orderPublisher.sendTextMessage()
    }

    private fun createOrder(productNames: List<String>, discounts: List<Discount>): Order {
        val products = productNames.asSequence().mapNotNull { name -> productsRepository.getProductByName(name) }.toList()
        return Order(products, discounts)
    }
}
