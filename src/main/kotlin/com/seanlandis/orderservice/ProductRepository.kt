package com.seanlandis.orderservice

interface ProductRepository {
    fun getProductByName(name: String): Product?
}