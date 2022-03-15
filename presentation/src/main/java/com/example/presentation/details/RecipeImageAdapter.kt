package com.example.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.RecipeImageItemBinding
import com.squareup.picasso.Picasso

class RecipeImageAdapter(private val images: List<String>) :
    RecyclerView.Adapter<RecipeImageAdapter.RecipeImageViewHolder>() {
    inner class RecipeImageViewHolder(private val binding: RecipeImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            Picasso.get()
                .load(item)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeImageViewHolder {
        val binding = RecipeImageItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}