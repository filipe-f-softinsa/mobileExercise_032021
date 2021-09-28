package com.example.mobileexercise_03_2021.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.network.ApiClient
import com.example.mobileexercise_03_2021.network.Repository
import kotlinx.coroutines.*

/**
 * Creates the ViewModel that allows to request the data and update it in the View
 */
class MainViewModel(application: Application) : AndroidViewModel(application){


    private var repository: Repository = Repository(ApiClient(application.cacheDir).service)

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
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val resultResponseBirds = repository.getBirds("1")
                if(resultResponseBirds.raw().cacheResponse() != null){
                    Log.e("NetworkResponseGetBirds", "Cached Body")
                }
                val resultBird = resultResponseBirds.body()
                Log.i("BirdList", resultBird.toString())
                if (resultBird != null) {
                    for (photo in resultBird.photos.photo) {
                        try {
                            val resultResponseImages = repository.getSizes(photo.id)
                            if(resultResponseImages.raw().cacheResponse() != null){
                                Log.e("NetworkResponseGetSizes", "Cached Body")
                            }
                            val resultImages = resultResponseImages.body()
                            if(resultImages != null){
                                val bird = Bird(photo.id, photo.title, resultImages.sizes.size)
                                birdList.add(bird)
                                _birdsLiveData.postValue(birdList)
                                Log.i("BirdList", "Added Bird ${bird.id}")
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            _birdsLiveData.postValue(birdList)
                        }
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
                _birdsLiveData.postValue(birdList)
            }
        }
    }
}