package com.droal.marvel.android

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droal.marvel.android.databinding.ListItemCharactersBinding
import com.droal.marvel.api.data.character.Character

class CharactersListAdapter(): RecyclerView.Adapter<CharactersListAdapter.CharactersListViewHolder>(){


    class CharactersListViewHolder(private var binding: ListItemCharactersBinding): RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.tvName
        val image: ImageView = binding.ivImg
        val desc: TextView = binding.tvShortDesc
    }

    var data = listOf<Character>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        return CharactersListViewHolder(ListItemCharactersBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.name
        holder.desc.text = item.description

        val imgUrl: String = item.thumbnail.path+"."+item.thumbnail.extension
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(holder.image.context)
            .load(imgUri)
            .circleCrop()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.err_img)
            .into(holder.image)
    }

    override fun getItemCount() = data.size

}