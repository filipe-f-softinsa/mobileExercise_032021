package com.example.mobileexercise_03_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileexercise_03_2021.adapters.ItemBirdAdapter
import com.example.mobileexercise_03_2021.databinding.ActivityMainBinding
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainTb)


    }

    private fun setupBirdRecyclerView(result : List<Bird>){
        binding.mainRv.layoutManager = GridLayoutManager(this,2)
        val adapter = ItemBirdAdapter(this,result)
        binding.mainRv.adapter = adapter
    }
}