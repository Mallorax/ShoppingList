package com.example.shoppinglist.ui.grocerylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        viewModel.isListArchived(args.groceriesListId).observe(viewLifecycleOwner, Observer { archived ->
            if (archived){
                binding.fabAddGrocery.visibility = View.INVISIBLE
            }else{
                binding.fabAddGrocery.visibility = View.VISIBLE
            }
            recyclerViewAdapter.isArchived = archived
        })

        recyclerViewAdapter = setupRecyclerViewAdapter()
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

    private fun showAddGroceryDialog(){
        val dialog = GroceryCreationDialog()
        dialog.show(requireActivity().supportFragmentManager, "GroceryCreationDialog")
    }

    private fun setupRecyclerViewAdapter(): GroceryListAdapter{
        return GroceryListAdapter(GroceryListAdapter.OnDeleteClickListener{grocery, view ->
            if (grocery != null) {
                viewModel.deleteGrocery(grocery.shoppingListId)
            }
        })
    }

    private fun collectGroceries(listId: Long){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadGroceries(listId).collectLatest {
                recyclerViewAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}