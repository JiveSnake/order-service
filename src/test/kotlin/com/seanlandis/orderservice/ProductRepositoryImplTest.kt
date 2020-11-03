package com.seanlandis.orderservice

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ProductRepositoryImplTest {
    private lateinit var productRepository: ProductRepositoryImpl

    @BeforeEach
    fun setUp() {
        productRepository = ProductRepositoryImpl()
        productRepository.initDatabase()
    }

    @Test
    fun givenInitializedRepo_whenGetProductByName_thenReturnProduct() {
        val result = productRepository.getProductByName("Apple")

        assertEquals(Product("apple", CurrencyAmount(0, 60)), result)
    }
}
