package com.example.mobileexercise_03_2021

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileexercise_03_2021.adapters.ItemBirdAdapter
import com.example.mobileexercise_03_2021.databinding.ActivityMainBinding
import com.example.mobileexercise_03_2021.databinding.DialogProgressBinding
import com.example.mobileexercise_03_2021.models.Bird
import com.example.mobileexercise_03_2021.viewModels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter = ItemBirdAdapter(this,ArrayList())
    private lateinit var progressDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainTb)
        setupBirdRecyclerView()

        viewModel.birdLiveData.observe(this,{
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
        if(list.isNotEmpty()){
            adapter.setListItems(list)
            hideProgressDialog()
            binding.mainRv.visibility = View.VISIBLE
            binding.mainTvNoData.visibility = View.GONE
        }
    }
}