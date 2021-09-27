package com.example.mobileexercise_03_2021.models

import com.squareup.moshi.Json

data class InfoSize(
    @Json(name="size")
    val size : List<Size>
)