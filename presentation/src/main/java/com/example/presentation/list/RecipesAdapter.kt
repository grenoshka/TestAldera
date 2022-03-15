package com.example.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.RecipeItem
import com.example.presentation.databinding.RecipeItemBinding
import com.squareup.picasso.Picasso

class RecipesAdapter(private val recipes: List<RecipeItem>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {
    private lateinit var onRecipeClicked: (uuid: String) -> Unit

    fun setOnClickListener(onRecipeClicked: (uuid: String) -> Unit) {
        this.onRecipeClicked = onRecipeClicked
    }

    inner class RecipeViewHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onRecipeClicked(getItem(bindingAdapterPosition).uuid)
            }
        }

        fun bind(item: RecipeItem) {
            binding.tvRecipeName.text = item.name
            binding.tvRecipeDescription.text = item.description
            Picasso.get()
                .load(item.images[0])
                .into(binding.ivRecipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    private fun getItem(position: Int) = recipes[position]
}