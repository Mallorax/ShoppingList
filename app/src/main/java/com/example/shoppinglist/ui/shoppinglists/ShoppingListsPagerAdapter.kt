package com.example.shoppinglist.ui.shoppinglists

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppinglist.model.appmodel.ShoppingList

class ShoppingListsPagerAdapter(val fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> ShoppingListsFragment()
            else -> ShoppingListsFragment()
        }
    }
}