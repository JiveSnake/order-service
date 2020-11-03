package com.seanlandis.orderservice

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*

internal class OrderServiceImplTest {
    private lateinit var orderService: OrderService
    private val mockProductRepository = mock(ProductRepository::class.java)

    @BeforeEach
    fun setUp() {
        orderService = OrderServiceImpl(mockProductRepository)
    }

    @Test
    fun givenSingleName_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val product = Product("apple", CurrencyAmount(1, 0))
        val expected = Order(listOf(product))
        `when`(mockProductRepository.getProductByName(anyString())).thenReturn(product)

        val result = orderService.calculateSubtotal(listOf("Apple"))

        assertEquals(expected.calculateSubtotal(), result)
    }

    @Test
    fun givenMultiNames_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val product = Product("apple", CurrencyAmount(1, 0))
        val expected = Order(listOf(product, product))
        `when`(mockProductRepository.getProductByName(anyString())).thenReturn(product)

        val result = orderService.calculateSubtotal(listOf("Apple", "Apple"))

        assertEquals(expected.calculateSubtotal(), result)
    }

    @Test
    fun givenEmptyString_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val result = orderService.calculateSubtotal(listOf(""))

        assertEquals(CurrencyAmount(0, 0), result)
    }
}
