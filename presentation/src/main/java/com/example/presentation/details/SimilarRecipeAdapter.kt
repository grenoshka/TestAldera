package com.example.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.SimilarRecipe
import com.example.presentation.databinding.SimilarRecipeItemBinding
import com.squareup.picasso.Picasso

class SimilarRecipeAdapter(private val similarRecipes: List<SimilarRecipe>) :
    RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder>() {
    private lateinit var onClickListener: (uuid: String) -> Unit


    fun setOnClickListener(onClickListener: (uuid: String) -> Unit) {
        this.onClickListener = onClickListener
    }

    inner class SimilarRecipeViewHolder(private val binding: SimilarRecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClickListener(getItem(bindingAdapterPosition).uuid)
            }
        }

        fun bind(item: SimilarRecipe) {
            binding.tvName.text = item.name
            Picasso.get()
                .load(item.image)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecipeViewHolder {
        val binding = SimilarRecipeItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarRecipeViewHolder, position: Int) {
        holder.bind(similarRecipes[position])
    }

    override fun getItemCount(): Int = similarRecipes.size

    private fun getItem(position: Int) = similarRecipes[position]
}