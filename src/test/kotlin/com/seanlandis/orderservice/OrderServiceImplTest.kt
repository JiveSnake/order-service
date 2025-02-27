package com.seanlandis.orderservice

import com.nhaarman.mockitokotlin2.argumentCaptor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class OrderServiceImplTest {
    private lateinit var orderService: OrderService
    private val mockOrderPublisher = mock(OrderPublisher::class.java)
    private val mockProductRepository = mock(ProductRepository::class.java)
    private val discounts = ArrayList<Discount>()

    @BeforeEach
    fun setUp() {
        orderService = OrderServiceImpl(mockOrderPublisher, mockProductRepository, discounts)
    }

    @Test
    fun givenSingleName_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val product = Product("apple", CurrencyAmount(1, 0))
        val expected = Order(listOf(product), discounts)
        `when`(mockProductRepository.getProductByName(anyString())).thenReturn(product)

        val result = orderService.calculateSubtotal(listOf("Apple"))

        assertEquals(expected.calculateSubtotal(), result)
    }

    @Test
    fun givenMultiNames_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val product = Product("apple", CurrencyAmount(1, 0))
        val expected = Order(listOf(product, product), discounts)
        `when`(mockProductRepository.getProductByName(anyString())).thenReturn(product)

        val result = orderService.calculateSubtotal(listOf("Apple", "Apple"))

        assertEquals(expected.calculateSubtotal(), result)
    }

    @Test
    fun givenEmptyString_whenCalculateSubtotal_thenReturnCorrectCurrencyAmount() {
        val result = orderService.calculateSubtotal(listOf(""))

        assertEquals(CurrencyAmount(0, 0), result)
    }

    @Test
    fun givenSingleNameAndDiscount_whenCalculateSubtotalWithDiscount_thenReturnCorrectCurrencyAmount() {
        val product = Product("apple", CurrencyAmount(1, 0))
        val expected = Order(listOf(product), discounts)
        `when`(mockProductRepository.getProductByName(anyString())).thenReturn(product)

        val result = orderService.calculateSubtotalWithDiscounts(listOf("Apple"))

        assertEquals(expected.calculateSubtotal(), result)
    }

    @Test
    fun givenEnoughString_whenSubmitOrder_thenReturnCorrectCurrencyAmount() {
        `when`(mockProductRepository.getStockByProductName(anyString())).thenReturn(1)

        orderService.submitOrder(listOf("apple"))

        argumentCaptor<String>().apply {
            verify(mockOrderPublisher, times(1)).sendTextMessage(capture())
            assertEquals("Order Successful", firstValue)
        }
    }

    @Test
    fun givenNotEnoughStock_whenSubmitorder_thenReturnNullWithCorrectMessage() {
        `when`(mockProductRepository.getStockByProductName(anyString())).thenReturn(1)

        orderService.submitOrder(listOf("apple", "apple"))

        argumentCaptor<String>().apply {
            verify(mockOrderPublisher, times(1)).sendTextMessage(capture())
            assertEquals("Not enough stock to complete order", firstValue)
        }
    }
}
