package com.example.shoppinglist.ui.shoppinglists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.databinding.ShoppingListsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShoppingListsFragment : Fragment() {

    private var _binding: ShoppingListsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListsViewModel by activityViewModels()
    private lateinit var adapter: ShoppingListsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShoppingListsFragmentBinding.inflate(inflater, container, false)
        adapter = setupRecyclerViewAdapter()
        val recyclerView = binding.shoppingListsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.fabAddShoppingList.setOnClickListener {
            showShoppingListDialog()
        }
        collectShoppingLists()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewAdapter(): ShoppingListsAdapter {
        return ShoppingListsAdapter(ShoppingListsAdapter.OnItemClickListener { shoppingList, view ->
            if (shoppingList != null){
                val action = ShoppingListsFragmentDirections
                    .actionShoppingListsFragmentToGroceriesListFragment(shoppingList.creation)
                view.findNavController().navigate(action)
            }
        })
    }

    private fun showShoppingListDialog() {
        val dialog = ShoppingListDialog()
        dialog.show(requireActivity().supportFragmentManager, "ShoppingListDialog")
    }

    private fun collectShoppingLists(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.shoppingList.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }
    }
}