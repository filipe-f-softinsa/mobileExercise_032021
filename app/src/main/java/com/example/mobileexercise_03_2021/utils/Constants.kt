package com.example.mobileexercise_03_2021.utils

object Constants {

    /**
     * Created to store hardcoded string in order to prevent miss spelling
     */
    const val API_KEY = "7bdb03d29144dbbabc9c71fd173ac356"
    const val BASE_URL = "https://api.flickr.com/"
    const val METHOD_SEARCH = "flickr.photos.search"
    const val METHOD_SIZE = "flickr.photos.getSizes"
    const val TAGS = "birds"
    const val FORMAT = "json"
    const val NO_JSON_CALLBACK = "1"
    const val LABEL = "Large"
    const val CACHE_SIZE : Long = (10 * 1024 * 1024).toLong()
    const val SHARED_PREFS = "shared_prefs"
    const val BIRD_LIST = "bird_list"
}