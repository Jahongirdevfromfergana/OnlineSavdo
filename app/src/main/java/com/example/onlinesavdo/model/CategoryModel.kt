package com.example.onlinesavdo.model

data class CategoryModel(
    val id: Int,
    val title: String,
    val icon: String,
    var checked: Boolean = false
)
