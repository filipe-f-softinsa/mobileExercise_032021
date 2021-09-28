package com.example.mobileexercise_03_2021.network

import android.content.Context
import com.example.mobileexercise_03_2021.utils.Constants
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *  Creates an reference to the Api and attaches a service
 */
class ApiClient(context: Context) {
    private val moshi : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val cache : Cache = Cache(context.cacheDir,Constants.CACHE_SIZE)
    private val okHttpClient : OkHttpClient = OkHttpClient.Builder().cache(cache).build()
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    val service : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}