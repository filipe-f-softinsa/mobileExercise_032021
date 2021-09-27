package com.example.mobileexercise_03_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileexercise_03_2021.adapters.ItemBirdAdapter
import com.example.mobileexercise_03_2021.databinding.ActivityMainBinding
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.utils.State
import com.example.mobileexercise_03_2021.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainTb)

        viewModel.birdLiveData.observe(this,{
            stateOfFetchingImages -> processState(stateOfFetchingImages)
        })
    }

    private fun setupBirdRecyclerView(result : List<Bird>){
        binding.mainRv.layoutManager = GridLayoutManager(this,2)
        val adapter = ItemBirdAdapter(this,result)
        binding.mainRv.adapter = adapter
    }

    private fun processState(state : State){
        when(state){
            is State.Loading -> {

            }
            is State.Success -> {
                if(state.data != null){
                    setupBirdRecyclerView(state.data)
                }
            }
            is State.Error -> {

            }
        }
    }
}