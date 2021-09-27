package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Creates an reference to the Api and attaches a service
 */
object ApiClient {
    private val retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val service : ApiService = retrofit.create(ApiService::class.java)
}