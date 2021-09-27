package com.example.mobileexercise_03_2021.network

import com.example.mobileexercise_03_2021.utils.Constants

/**
 * Using MVVM architecture it is recommended to create a repository that fetches the data
 */
class Repository(private val apiService: ApiService) {

    suspend fun getBirds(page : String) = apiService.getBirds(Constants.METHOD_SEARCH, Constants.API_KEY, Constants.TAGS, page, Constants.FORMAT, Constants.NO_JSON_CALLBACK)

    suspend fun getSizes(id : String) = apiService.getSizes(Constants.METHOD_SIZE, Constants.API_KEY, id, Constants.FORMAT, Constants.NO_JSON_CALLBACK)
}