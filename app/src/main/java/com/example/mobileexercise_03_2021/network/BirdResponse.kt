package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.models.InfoPhotos
import com.squareup.moshi.Json

data class BirdResponse(
    @Json(name="photos")
    val photos : InfoPhotos
)
