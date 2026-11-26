package com.example.najah_tie_mobile.Photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.najah_tie_mobile.data.model.PhotoModel
import com.example.najah_tie_mobile.databinding.ItemPhotoBinding

class PhotoAdapter(private val items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvAuthor.text = item.author
        Glide.with(holder.itemView.context)
            .load(item.download_url)
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int = items.size
}