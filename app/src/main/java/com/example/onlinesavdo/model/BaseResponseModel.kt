package com.example.onlinesavdo.model

data class BaseResponseModel<T>(
    val success: Boolean,
    val data: T,
    val message: String,
    val error_code: Int
)


