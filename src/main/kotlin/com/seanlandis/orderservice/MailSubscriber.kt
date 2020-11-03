package com.seanlandis.orderservice

import org.springframework.jms.annotation.JmsListener


class MailSubscriber {
    @JmsListener(destination = "orderSubmitted")
    fun receiveMessage(msg: String) {
        print(msg)
    }
}
