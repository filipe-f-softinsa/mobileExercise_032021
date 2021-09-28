package com.example.mobileexercise_03_2021.models

import com.squareup.moshi.Json

data class Photo(
    @Json(name="id")
    val id : String,
    @Json(name="owner")
    val owner : String,
    @Json(name="title")
    val title : String
)
