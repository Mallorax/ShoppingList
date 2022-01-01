package com.example.shoppinglist.ui.archivedlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.databinding.ArchivedListsFragmentBinding
import com.example.shoppinglist.ui.ViewPagerFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArchivedListsFragment: Fragment() {

    private var _binding: ArchivedListsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArchivedListsViewModel by viewModels()
    private lateinit var adapter: ArchivedListsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArchivedListsFragmentBinding.inflate(inflater, container, false)
        adapter = setupRecyclerViewAdapter()
        val recyclerView = binding.archivedListsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        collectArchivedLists()
        return binding.root

    }
    private fun collectArchivedLists(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.archivedLists.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }
    }


    private fun setupRecyclerViewAdapter(): ArchivedListsAdapter{
        return ArchivedListsAdapter(ArchivedListsAdapter.OnItemClickListener{ archivedList, view ->
            if (archivedList != null){
                val action = ViewPagerFragmentDirections
                    .actionShoppingListsFragmentToGroceriesListFragment(archivedList.creation)
                view.findNavController().navigate(action)
            }
        })
    }
}