package com.example.mobileexercise_03_2021.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.network.ApiClient
import com.example.mobileexercise_03_2021.network.Repository
import com.example.mobileexercise_03_2021.utils.Constants
import com.example.mobileexercise_03_2021.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Creates the ViewModel that allows to request the data and update it in the View
 */

class MainViewModel(private val repository: Repository = Repository(ApiClient.service)) : ViewModel(){
    private var _birdsLiveData = MutableLiveData<State>()
    val birdLiveData : LiveData<State>
        get() = _birdsLiveData

    init {
        fetchBirds()
    }

    private fun fetchBirds(){
        val birdList = ArrayList<Bird>()
        /**
         * Use Dispatchers.IO for coroutines that fetch data
         */
        _birdsLiveData.postValue(State.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val responseBirdCall = repository.getBirds("1")
                launch(Dispatchers.Default) {
                    for (photo in responseBirdCall.photos.photo){
                        launch(Dispatchers.IO) {
                            try {
                                Log.i("Photo", photo.id)
                                val responseSizeCall = repository.getSizes(photo.id)
                                for(size in responseSizeCall.sizes.size){
                                    if(size.label == Constants.LABEL){
                                        val bird = Bird(photo.id,photo.title,size.source)
                                        birdList.add(bird)
                                        Log.i("Bird", "Added ${photo.id}")

                                    }
                                }
                            }catch (e : Exception) {
                                Log.e("getSizes", e.message!!)
                                _birdsLiveData.postValue(State.Error(null,e.message!!))
                            }
                        }
                    }
                    _birdsLiveData.postValue(State.Success(birdList))
                }
            }catch (e : Exception){
                Log.e("getBirds", e.message!!)
                _birdsLiveData.postValue(State.Error(null,e.message!!))
            }
        }
    }
}