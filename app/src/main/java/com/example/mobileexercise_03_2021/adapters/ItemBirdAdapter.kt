package com.example.mobileexercise_03_2021.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileexercise_03_2021.R
import com.example.mobileexercise_03_2021.databinding.ItemBirdBinding
import com.example.mobileexercise_03_2021.models.Bird

class ItemBirdAdapter(private val context: Context,private var list : List<Bird>) : RecyclerView.Adapter<ItemBirdAdapter.ViewHolder>() {

    private var onClickListenerItemBird : OnClickListenerItemBird? = null

    class ViewHolder(val binding: ItemBirdBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnClickListenerItemBird{
        fun onClickItem(uri: Uri)
    }

    fun setOnClickListenerItemBird(onClickListenerItemBird: OnClickListenerItemBird){
        this.onClickListenerItemBird = onClickListenerItemBird
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBirdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bird = list[position]

        holder.binding.birdTvTitle.text = bird.title
        /**
         * Use glide to load image into ImageView
         * bird.images[1] -> label = "Large Square"
         * bird.images[9] -> label = "Large"
         */
        Glide.with(context).load(bird.images[1].source).into(holder.binding.birdTvImage)

        holder.binding.root.setOnClickListener {
            onClickListenerItemBird?.onClickItem(Uri.parse(bird.images[9].source))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setListItems(newList : List<Bird>){
        this.list = newList
        notifyDataSetChanged()
    }
}