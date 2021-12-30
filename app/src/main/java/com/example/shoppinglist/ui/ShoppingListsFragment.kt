package com.example.shoppinglist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppinglist.databinding.ShoppingListsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListsFragment : Fragment() {

    private var _binding: ShoppingListsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShoppingListsFragmentBinding.inflate(inflater, container, false)
        val adapter = setupRecyclerViewAdapter()
        val recyclerView = binding.shoppingListsRecyclerview
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        viewModel.shoppingList.observe(this.viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewAdapter(): ShoppingListsAdapter{
        return ShoppingListsAdapter(ShoppingListsAdapter.OnItemClickListener{ shoppingList, view ->
            Snackbar.make(view, shoppingList?.listName.orEmpty(), Snackbar.LENGTH_LONG).show()
        })
    }
}