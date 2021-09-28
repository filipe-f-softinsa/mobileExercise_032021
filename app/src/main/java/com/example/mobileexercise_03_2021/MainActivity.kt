package com.example.mobileexercise_03_2021

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileexercise_03_2021.adapters.ItemBirdAdapter
import com.example.mobileexercise_03_2021.databinding.ActivityMainBinding
import com.example.mobileexercise_03_2021.databinding.DialogProgressBinding
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.utils.Constants
import com.example.mobileexercise_03_2021.viewModels.MainViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val adapter = ItemBirdAdapter(this,ArrayList())
    private lateinit var progressDialog : Dialog
    private lateinit var sharedPreferences: SharedPreferences
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java,Bird::class.java)
    private val jsonAdapter : JsonAdapter<List<Bird>> = moshi.adapter(type)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainTb)
        setupBirdRecyclerView()
        mainViewModel= MainViewModel(this)
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS,Context.MODE_PRIVATE)



        mainViewModel.birdLiveData.observe(this,{
            fetchedBirds ->
            processState(fetchedBirds)
        })
    }

    private fun setupBirdRecyclerView(){
        binding.mainRv.layoutManager = GridLayoutManager(this,2)
        adapter.setOnClickListenerItemBird(object : ItemBirdAdapter.OnClickListenerItemBird{
            override fun onClickItem(uri : Uri) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(uri, "image/*")
                startActivity(intent)
            }
        })
        binding.mainRv.adapter = this.adapter
        showProgressDialog()
    }

    private fun showProgressDialog(){
        val bindingDialog = DialogProgressBinding.inflate(layoutInflater)
        progressDialog = Dialog(this)
        progressDialog.setContentView(bindingDialog.root)

        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
    }

    private fun hideProgressDialog(){
        progressDialog.dismiss()
    }

    private fun processState(list : List<Bird>){
        hideProgressDialog()
        if(list.isNotEmpty()){
            adapter.setListItems(list)
            binding.mainRv.visibility = View.VISIBLE
            binding.mainTvNoData.visibility = View.GONE

            val editor = sharedPreferences.edit()
            editor.putString(Constants.BIRD_LIST,jsonAdapter.toJson(list))
            editor.apply()

        }else{
            Toast.makeText(this,"Connection timeout.Please check your internet connection and restart the application",Toast.LENGTH_LONG).show()
            val listFromSharedPreferences = sharedPreferences.getString(Constants.BIRD_LIST,null)
            if(listFromSharedPreferences != null){
                adapter.setListItems(jsonAdapter.fromJson(listFromSharedPreferences)!!)
                binding.mainRv.visibility = View.VISIBLE
                binding.mainTvNoData.visibility = View.GONE
            }
        }
    }
}