package com.seanlandis.orderservice

interface ProductRepository {
    fun getProductByName(name: String): Product?

    fun getStockByProductName(name: String): Int?

    fun reduceProductStock(name: String, count: Int)
}
