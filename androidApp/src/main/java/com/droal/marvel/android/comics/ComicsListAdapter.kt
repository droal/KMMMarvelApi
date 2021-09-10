package com.droal.marvel.android.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droal.marvel.android.R
import com.droal.marvel.android.databinding.ListItemComicsBinding
import com.droal.marvel.comics.domain.Comic

class ComicsListAdapter (): RecyclerView.Adapter<ComicsListAdapter.ComicsListViewHolder>(){


    class ComicsListViewHolder(private var binding: ListItemComicsBinding): RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.tvName
        val image: ImageView = binding.ivImg
        val desc: TextView = binding.tvShortDesc
        val isbn: TextView = binding.tvIsbn
    }

    var data = listOf<Comic>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        return ComicsListViewHolder(ListItemComicsBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        val item = data[position]

        holder.name.text = item.title
        holder.desc.text = item.description
        holder.isbn.text = "ISBN: "+ item.isbn

        //val imgUrl: String = item.thumbnailDto.path+"."+item.thumbnailDto.extension
        val imgUrl: String = item.thumbnailPath
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