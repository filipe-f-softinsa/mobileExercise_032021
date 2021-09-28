package com.example.mobileexercise_03_2021.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.models.Photo
import com.example.mobileexercise_03_2021.models.Size
import com.example.mobileexercise_03_2021.network.ApiClient
import com.example.mobileexercise_03_2021.network.BirdResponse
import com.example.mobileexercise_03_2021.network.Repository
import kotlinx.coroutines.*

/**
 * Creates the ViewModel that allows to request the data and update it in the View
 */
class MainViewModel(private val repository: Repository = Repository(ApiClient.service)) : ViewModel(){

    /**
     * Creates a live data that will observe the data posted in fetchBirds
     */
    private var _birdsLiveData = MutableLiveData<List<Bird>>()
    val birdLiveData : LiveData<List<Bird>>
        get() = _birdsLiveData

    /**
     * Once created this object it will immediately start the fetchBirds function
     */
    init {
        fetchBirds()
    }

    private fun fetchBirds(){
        val birdList = ArrayList<Bird>()

        /**
         * Uses coroutines in order to perform the task safely in the background
         * Dispatchers.IO is used doe to being the recommended dispatcher for performing connections and fetch data to apis
         */
        _birdsLiveData.postValue(birdList)
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val result = repository.getBirds("1")
                for (photo in result.photos.photo) {
                    try {
                        val resultImages = repository.getSizes(photo.id)
                        val bird = Bird(photo.id, photo.title, resultImages.sizes.size)
                        birdList.add(bird)
                        _birdsLiveData.postValue(birdList)
                        Log.i("BirdList", "Added Bird ${bird.id}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }
}