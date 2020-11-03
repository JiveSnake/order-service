package com.seanlandis.orderservice

class Order(private val products: List<Product>) {
    fun calculateSubtotal(): CurrencyAmount {
        return products.asSequence()
                .mapNotNull { product -> product.price }
                .fold(CurrencyAmount(0, 0)) { price1, price2 -> price1 + price2 }
    }
}
