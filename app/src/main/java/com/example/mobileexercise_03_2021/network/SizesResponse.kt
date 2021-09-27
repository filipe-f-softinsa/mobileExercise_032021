package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.models.InfoSize
import com.squareup.moshi.Json

data class SizesResponse(
    @Json(name="sizes")
    val sizes : InfoSize
)
