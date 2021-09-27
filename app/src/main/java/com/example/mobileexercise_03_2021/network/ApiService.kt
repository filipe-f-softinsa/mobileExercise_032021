package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Creates a service to use once the connection to the api is defined
 * it allows to generate the requests to the API defining the queries
 */

interface ApiService {

    @GET("services/rest/")
    suspend fun getBirds(@Query("method") method: String,
                         @Query("api_key") apiKey : String,
                         @Query("tags") tags : String,
                         @Query("page") page : String,
                         @Query("format") format : String,
                         @Query("nojsoncallback") noJsonCallback : String
    ) : BirdResponse

    @GET("service/rest/")
    suspend fun getSizes(@Query("method") method: String,
                         @Query("api_key") apiKey : String,
                         @Query("photo_id") id : String,
                         @Query("format") format : String,
                         @Query("nojsoncallback") noJsonCallback : String
    ) : SizesResponse

}