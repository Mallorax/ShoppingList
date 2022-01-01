package com.example.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppinglist.databinding.ViewPagerFragmentBinding
import com.example.shoppinglist.ui.shoppinglists.ShoppingListsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment: Fragment() {

    private lateinit var _binding: ViewPagerFragmentBinding
    private val binding get() = _binding
    private lateinit var adapter: ShoppingListsPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewPagerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = binding.tabLayout
        val pager = binding.pager
        val adapter = ShoppingListsPagerAdapter(this)
        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager){ tab, postition ->
            tab.text = postition.toString()
        }.attach()
    }
}