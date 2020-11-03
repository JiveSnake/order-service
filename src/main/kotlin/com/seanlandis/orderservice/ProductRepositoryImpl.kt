package com.seanlandis.orderservice

import javax.annotation.PostConstruct

class ProductRepositoryImpl : ProductRepository {
    private val productsByName: MutableMap<String, Product> = HashMap()

    @PostConstruct
    fun initDatabase() {
        productsByName["apple"] = Product("apple", CurrencyAmount(0, 60))
        productsByName["orange"] = Product("orange", CurrencyAmount(0, 25))
    }

    override fun getProductByName(name: String): Product? {
        return productsByName[name.toLowerCase().trim()]
    }
}
