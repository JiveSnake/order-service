package com.seanlandis.orderservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsTemplate


@Configuration
class Config {
    @Bean
    fun mailSubscriber() = MailSubscriber()

    @Bean
    fun orderService(orderPublisher: OrderPublisher, productRepository: ProductRepository) = OrderServiceImpl(
            orderPublisher,
            productRepository,
            listOf(
                    Discount("apple", 1, 2),
                    Discount("orange", 2, 3)
            )
    )

    @Bean
    fun orderPublisher(jmsTemplate: JmsTemplate) = OrderPublisherImpl(jmsTemplate)

    @Bean
    fun productRepository() = ProductRepositoryImpl()
}
