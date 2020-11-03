package com.seanlandis.orderservice

import com.nhaarman.mockitokotlin2.argumentCaptor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*


internal class OrderControllerTest {
    private lateinit var orderController: OrderController
    private val orderService = mock(OrderService::class.java)

    @BeforeEach
    fun setUp() {
        orderController = OrderController(orderService)
    }

    @Test
    fun giveCommaSeparatedString_whenSubtotal_thenCallServiceWithListOfStrings() {
        `when`(orderService.calculateSubtotal(anyList())).thenReturn(CurrencyAmount(0, 0))
        orderController.subtotal("Apple,Orange")

        argumentCaptor<List<String>>().apply {
            verify(orderService, times(1)).calculateSubtotal(capture())
            assertEquals(listOf("Apple", "Orange"), firstValue)
        }
    }
}
