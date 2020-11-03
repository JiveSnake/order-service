package com.seanlandis.orderservice

interface OrderPublisher {
    fun sendTextMessage(message: String)
}
