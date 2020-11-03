package com.seanlandis.orderservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
//    @Bean
//    fun orderController(orderService: OrderServiceImpl) = OrderController(orderService)

    @Bean
    fun orderService(productRepository: ProductRepository) = OrderServiceImpl(productRepository)

    @Bean
    fun productRepository() = ProductRepositoryImpl()
}