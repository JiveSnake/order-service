package com.seanlandis.orderservice

interface OrderService {
    fun calculateSubtotal(productNames: List<String>): CurrencyAmount

    fun calculateSubtotalWithDiscounts(productNames: List<String>): CurrencyAmount

    fun submitOrder(productNames: List<String>)
}