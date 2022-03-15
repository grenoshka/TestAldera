package com.example.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.RecipesFragmentBinding
import com.example.presentation.util.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class RecipesFragment : BaseFragment() {

    private val viewModel: RecipesViewModel by viewModels()
    private var _binding: RecipesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipesFragmentBinding.inflate(inflater, container, false)

        binding.rvRecipes.layoutManager = LinearLayoutManager(context)
        viewModel.getRecipes()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading
            .onEach { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            .launchWhenStarted(lifecycleScope)
        viewModel.recipes
            .onEach { recipes ->
                val recipesAdapter = RecipesAdapter(recipes)
                binding.rvRecipes.adapter = recipesAdapter
                recipesAdapter.setOnClickListener { uuid ->
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(uuid)
                    findNavController().navigate(action)
                }
            }
            .launchWhenStarted(lifecycleScope)
        viewModel.error
            .onEach { errorEntity ->
                processError(errorEntity)
            }
            .launchWhenStarted(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}