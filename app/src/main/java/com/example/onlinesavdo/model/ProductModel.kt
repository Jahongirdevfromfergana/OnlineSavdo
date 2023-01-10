package com.example.onlinesavdo.model

import java.io.Serializable

data class ProductModel(
    val id: Int,
    val name: String,
    val price: String,
    val image: String,
    var cartCount: Int
): Serializable