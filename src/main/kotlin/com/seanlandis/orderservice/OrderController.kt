package com.seanlandis.orderservice

import org.springframework.context.annotation.Profile
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@Profile("!test")
@ShellComponent
class OrderController(private val orderService: OrderService) {
    @ShellMethod("Calculate subtotal of comma-separated string of product names")
    fun subtotal(@ShellOption products: String?): String {
        val subtotal = products
                ?.split(",")
                ?.let { orderService.calculateSubtotal(it) }
        return subtotal.toString()
    }
}
