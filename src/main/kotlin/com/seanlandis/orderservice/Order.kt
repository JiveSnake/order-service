package com.seanlandis.orderservice

class Order(products: List<Product>, private val discounts: List<Discount>) {
     var productCounts: MutableMap<Product, Int> = HashMap()

    init {
        products.forEach {
            val count = productCounts.getOrDefault(it, 0)
            productCounts[it] = count + 1
        }
    }

    fun calculateSubtotal(): CurrencyAmount {
        return productCounts.asSequence()
                .mapNotNull { productCount -> calculateRawSubtotal(productCount.key, productCount.value) }
                .fold(CurrencyAmount(0, 0)) { price1, price2 -> price1 + price2 }
    }

    private fun calculateRawSubtotal(product: Product, count: Int): CurrencyAmount {
        return CurrencyAmount(product.price.dollar, product.price.cent) * checkForDiscounts(product.name, count)
    }

    private fun checkForDiscounts(productName: String, count: Int): Int {
        var modifiedCount = count
        for (discount in discounts) {
            if (discount.productName == productName) modifiedCount = applyDiscount(discount, count)
        }
        return modifiedCount
    }

    private fun applyDiscount(discount: Discount, count: Int): Int {
        val quotient = (count * discount.dividend) / discount.divisor
        val remainder = (count * discount.dividend) % discount.divisor
        return if (remainder > 0) quotient + 1 else quotient
    }
}
