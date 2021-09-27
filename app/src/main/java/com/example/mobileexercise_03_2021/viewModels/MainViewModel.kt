package com.example.mobileexercise_03_2021.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.network.ApiClient
import com.example.mobileexercise_03_2021.network.Repository
import com.example.mobileexercise_03_2021.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class MainViewModel(private val repository: Repository = Repository(ApiClient.service)) : ViewModel(){
    private var _birdsLiveData = MutableLiveData<List<Bird>>()
    val birdLiveData : LiveData<List<Bird>>
        get() = _birdsLiveData

    init {
        fetchBirds()
    }

    private fun fetchBirds(){
        val birdList = ArrayList<Bird>()
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val responseBirdCall = repository.getBirds("1")
                for (photo in responseBirdCall.photos.photo){
                    try {
                        val responseSizeCall = repository.getSizes(photo.id)
                        for(size in responseSizeCall.sizes.size){
                            if(size.label == Constants.LABEL){
                                val bird = Bird(photo.id,size.source)
                                birdList.add(bird)
                            }
                        }
                    }catch (e : SocketTimeoutException){
                        e.printStackTrace()
                    }catch (e : IOException){
                        e.printStackTrace()
                    }
                }
            }catch (e : SocketTimeoutException){
                e.printStackTrace()
            }catch (e : IOException){
                e.printStackTrace()
            }finally {
                _birdsLiveData.postValue(birdList)
            }
        }
    }
}