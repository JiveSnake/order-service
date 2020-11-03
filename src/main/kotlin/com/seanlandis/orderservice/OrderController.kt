package com.seanlandis.orderservice

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class OrderController(private val orderService: OrderService) {
    @ShellMethod("Calculate subtotal of comma-separated string of product names.")
    fun subtotal(products: String, @ShellOption(arity = 1, defaultValue = "false") applyDiscounts: Boolean): String {
        val subtotal = products
                .split(",")
                .let {
                    if (applyDiscounts) orderService.calculateSubtotalWithDiscounts(it)
                    else orderService.calculateSubtotal(it)
                }
        return subtotal.toString()
    }

    @ShellMethod("Submit an order for the comma-separated string of product names.")
    fun order(products: String) {
        products.split(",").let { orderService.submitOrder(it) }
    }
}
