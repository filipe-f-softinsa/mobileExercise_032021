package com.example.mobileexercise_03_2021.models

import com.squareup.moshi.Json

data class Size(
    @Json(name="label")
    val label : String,
    @Json(name="width")
    val width : Int,
    @Json(name="height")
    val height : Int,
    @Json(name="source")
    val source : String,
    @Json(name="media")
    val media : String
)
