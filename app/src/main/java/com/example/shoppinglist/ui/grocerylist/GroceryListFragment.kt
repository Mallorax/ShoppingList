package com.example.shoppinglist.ui.grocerylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.databinding.GroceriesListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroceryListFragment : Fragment() {

    private var _binding: GroceriesListFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: GroceryListFragmentArgs by navArgs()
    private val viewModel: GroceryListViewModel by activityViewModels()
    private lateinit var recyclerViewAdapter: GroceryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GroceriesListFragmentBinding.inflate(inflater, container, false)

        recyclerViewAdapter = GroceryListAdapter()
        val recyclerView = binding.groceryListRecycler
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.fabAddGrocery.setOnClickListener {
            showAddGroceryDialog()
        }

        collectGroceries(args.groceriesListId)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun showAddGroceryDialog(){
        val dialog = GroceryCreationDialog()
        dialog.show(requireActivity().supportFragmentManager, "GroceryCreationDialog")
    }

    private fun collectGroceries(listId: Long){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadGroceries(listId).collect {
                recyclerViewAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}