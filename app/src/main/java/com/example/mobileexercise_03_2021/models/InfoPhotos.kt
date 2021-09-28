package com.example.mobileexercise_03_2021.models
import com.squareup.moshi.Json

data class InfoPhotos(
    @Json(name="page")
    val page : Int,
    @Json(name="pages")
    val pages : Long,
    @Json(name="perpage")
    val perpage : Int,
    @Json(name="total")
    val total : Long,
    @Json(name="photo")
    val photo : List<Photo>
)