package com.seanlandis.orderservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
    fun orderService(productRepository: ProductRepository) = OrderServiceImpl(
            productRepository,
            listOf(
                    Discount("apple", 1, 2),
                    Discount("orange", 2, 3)
            )
    )

    @Bean
    fun productRepository() = ProductRepositoryImpl()
}