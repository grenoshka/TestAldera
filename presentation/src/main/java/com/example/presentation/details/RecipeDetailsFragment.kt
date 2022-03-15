package com.example.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.RecipeDetails
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.RecipeDetailsFragmentBinding
import com.example.presentation.util.fromHtml
import com.example.presentation.util.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RecipeDetailsFragment : BaseFragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private var _binding: RecipeDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipeDetailsFragmentBinding.inflate(inflater, container, false)

        viewModel.getRecipeDetails(args.recipeUuid)
        binding.rvSimilar.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading
            .onEach { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            .launchWhenStarted(lifecycleScope)
        viewModel.recipeDetails
            .onEach { recipeDetails ->
                bindRecipeDetails(recipeDetails)
            }
            .launchWhenStarted(lifecycleScope)
        viewModel.error
            .onEach { errorEntity ->
                processError(errorEntity)
            }
            .launchWhenStarted(lifecycleScope)
    }

    private fun bindRecipeDetails(recipeDetails: RecipeDetails) {
        with(recipeDetails) {
            binding.tvName.text = name
            binding.vpImages.adapter = RecipeImageAdapter(images)
            binding.tvDescription.text = description
            binding.tvInstruction.text = instructions.fromHtml()
            binding.tvDifficulty.text =
                String.format(getString(R.string.recipe_details_difficulty), difficulty.number)

            val similarRecipesAdapter = SimilarRecipeAdapter(similar)
            binding.rvSimilar.adapter = similarRecipesAdapter
            similarRecipesAdapter.setOnClickListener {
                val action = RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentSelf(it)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}