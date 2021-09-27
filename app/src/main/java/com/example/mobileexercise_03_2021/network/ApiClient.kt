package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.utils.Constants
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *  Creates an reference to the Api and attaches a service
 */
object ApiClient {
    private val moshi : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    val service : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}