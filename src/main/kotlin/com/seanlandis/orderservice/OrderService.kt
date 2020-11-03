package com.seanlandis.orderservice

interface OrderService {
    fun calculateSubtotal(productNames: List<String>): CurrencyAmount
}