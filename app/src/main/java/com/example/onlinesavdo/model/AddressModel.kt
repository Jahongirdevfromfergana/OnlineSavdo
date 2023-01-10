package com.example.onlinesavdo.model

import java.io.Serializable

data class AddressModel(
    var address: String,
    var latitude: Double,
    var longitude: Double
): Serializable
