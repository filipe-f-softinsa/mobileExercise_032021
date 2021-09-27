package com.example.mobileexercise_03_2021.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileexercise_03_2021.databinding.ItemBirdBinding
import com.example.mobileexercise_03_2021.models.Bird

class ItemBirdAdapter(private val context: Context,private val list : List<Bird>) : RecyclerView.Adapter<ItemBirdAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBirdBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBirdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bird = list[position]

        holder.binding.birdTvTitle.text = bird.title
        Glide.with(context).load(Uri.parse(bird.image)).into(holder.binding.birdTvImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}