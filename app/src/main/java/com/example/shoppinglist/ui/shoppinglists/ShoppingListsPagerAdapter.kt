package com.example.shoppinglist.ui.shoppinglists

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppinglist.ui.archivedlists.ArchivedListsFragment

class ShoppingListsPagerAdapter(val fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ShoppingListsFragment()
            else -> ArchivedListsFragment()
        }
    }
}