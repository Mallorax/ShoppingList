package com.example.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ViewPagerFragmentBinding
import com.example.shoppinglist.ui.shoppinglists.ShoppingListsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment: Fragment() {

    private lateinit var _binding: ViewPagerFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewPagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        val tabLayout = binding.tabLayout
        val pager = binding.pager
        val adapter = ShoppingListsPagerAdapter(this)
        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager){ tab, postition ->
            when(postition){
                0 -> {
                    tab.text = getString(R.string.shopping_lists)
                    tab.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_baseline_format_list_bulleted_24)
                }else -> {
                tab.text = getString(R.string.archive)
                tab.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_baseline_archive_24)
                }
            }
        }.attach()
    }
}