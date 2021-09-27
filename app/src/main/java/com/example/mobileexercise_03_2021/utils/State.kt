package com.example.mobileexercise_03_2021.utils

import com.example.mobileexercise_03_2021.models.Bird

sealed class State(val data : List<Bird>? = null, val message : String? = null) {

    class Success(data:List<Bird>?) : State(data)

    class Loading(data: List<Bird>? = null) : State(data)

    class Error(data: List<Bird>? = null, message: String?) : State(data,message)
}