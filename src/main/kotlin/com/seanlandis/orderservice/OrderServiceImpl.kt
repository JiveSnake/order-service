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
        var message = "Order Successful"
        if (orderIsPossible(productNames)) {
            val order = createOrder(productNames, discounts)
            for (productCount in order.productCounts) {
                productsRepository.reduceProductStock(productCount.key.name, productCount.value)
            }
        } else message = "Not enough stock to complete order"
        orderPublisher.sendTextMessage(message)
    }

    private fun orderIsPossible(productNames: List<String>): Boolean {
        var orderIsPossible = true
        val productCounts = HashMap<String, Int>()
        for (name in productNames) {
            val count = productCounts.getOrDefault(name, 0)
            productCounts[name] = count + 1
        }
        for (productCount in productCounts) {
            val stock = productsRepository.getStockByProductName(productCount.key)
            if (stock == null || stock < productCount.value) orderIsPossible = false
        }

        return orderIsPossible
    }

    private fun createOrder(productNames: List<String>, discounts: List<Discount>): Order {
        val products = productNames.asSequence().mapNotNull { name -> productsRepository.getProductByName(name) }.toList()
        return Order(products, discounts)
    }
}
