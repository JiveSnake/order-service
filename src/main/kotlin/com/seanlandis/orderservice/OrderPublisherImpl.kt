package com.seanlandis.orderservice

import org.apache.activemq.command.ActiveMQTextMessage
import org.springframework.jms.core.JmsTemplate

class OrderPublisherImpl(private val jmsTemplate: JmsTemplate) : OrderPublisher {
    override fun sendTextMessage() {
        val msg = ActiveMQTextMessage()
        msg.text = "Order Submitted"
        jmsTemplate.send("orderSubmitted") { msg }
    }
}
