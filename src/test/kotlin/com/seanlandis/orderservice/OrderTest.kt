package com.seanlandis.orderservice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OrderTest {
    private val products = ArrayList<Product>()
    private val discounts = ArrayList<Discount>()

    @Test
    fun givenOrderWithOneProduct_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        products.add(Product("apple", CurrencyAmount(0, 60)))
        val order = Order(products, discounts)

        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 60), result)
    }

    @Test
    fun givenOrderWithMultipleProducts_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        products.add(Product("apple", CurrencyAmount(0, 60)))
        products.add(Product("orange", CurrencyAmount(0, 25)))
        val order = Order(products, discounts)

        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 85), result)
    }
    @Test
    fun givenOrderWithZeroProducts_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val order = Order(products, discounts)

        val result = order.calculateSubtotal()
        assertEquals(CurrencyAmount(0, 0), result)
    }

    @Test
    fun givenOrderWithProductsAndDiscounts_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        discounts.add(Discount("apple", 1, 2))
        discounts.add(Discount("orange", 2, 3))
        products.add(Product("apple", CurrencyAmount(0, 60)))
        products.add(Product("apple", CurrencyAmount(0, 60)))
        products.add(Product("orange", CurrencyAmount(0, 25)))
        products.add(Product("orange", CurrencyAmount(0, 25)))
        products.add(Product("orange", CurrencyAmount(0, 25)))
        val order = Order(products, discounts)

        val result = order.calculateSubtotal()

        assertEquals(CurrencyAmount(1, 10), result)
    }
}
