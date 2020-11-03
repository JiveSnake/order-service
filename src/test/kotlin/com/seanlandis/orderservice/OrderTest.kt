package com.seanlandis.orderservice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OrderTest {
    private lateinit var order: Order
    private val products = ArrayList<Product>()

    @BeforeEach
    fun setUp() {
        order = Order(products)
    }

    @Test
    fun givenOrderWithOneProduct_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        products.add(Product("apple", CurrencyAmount(0, 60)))

        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 60), result)
    }

    @Test
    fun givenOrderWithMultipleProducts_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        products.add(Product("apple", CurrencyAmount(0, 60)))
        products.add(Product("orange", CurrencyAmount(0, 25)))

        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 85), result)
    }
    @Test
    fun givenOrderWithZeroProducts_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 0), result)
    }
}
