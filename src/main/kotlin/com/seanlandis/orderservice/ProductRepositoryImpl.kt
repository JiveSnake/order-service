package com.seanlandis.orderservice

import javax.annotation.PostConstruct

class ProductRepositoryImpl : ProductRepository {
    private val productsByName: MutableMap<String, Product> = HashMap()
    private val productStock: MutableMap<String, Int?> = HashMap()

    @PostConstruct
    fun initDatabase() {
        productsByName["apple"] = Product("apple", CurrencyAmount(0, 60))
        productsByName["orange"] = Product("orange", CurrencyAmount(0, 25))

        productStock["apple"] = 2
        productStock["orange"] = 3
    }

    override fun getProductByName(name: String): Product? {
        return productsByName[name.toLowerCase().trim()]
    }

    override fun getStockByProductName(name: String): Int? {
        return productStock[name]
    }

    override fun reduceProductStock(name: String, count: Int) {
        productStock[name] = productStock[name]?.minus(count)
    }
}
