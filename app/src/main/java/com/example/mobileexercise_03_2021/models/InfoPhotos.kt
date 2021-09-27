package com.example.mobileexercise_03_2021.models

data class InfoPhotos(
    val page : Int,
    val pages : Long,
    val perpage : Int,
    val total : Long,
    val photo : List<Photo>
)