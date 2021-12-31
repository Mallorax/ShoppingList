package com.example.shoppinglist.ui.grocerylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.GroceriesListItemBinding
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList

class GroceryListAdapter(): ListAdapter<Grocery, GroceryListAdapter.GroceryListVH>(DiffCallback){

    override fun onBindViewHolder(holder: GroceryListVH, position: Int) {
        val shoppingList = getItem(position)
        holder.bind(shoppingList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroceryListVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GroceriesListItemBinding.inflate(inflater, parent, false)
        return GroceryListVH(binding)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Grocery>(){
        override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
            return oldItem.name === newItem.name && oldItem.name === newItem.name
        }

        override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
            return oldItem.name == newItem.name && oldItem.amount == newItem.amount
        }
    }

    class GroceryListVH(val binding: GroceriesListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(grocery: Grocery){
            binding.grocery = grocery
            binding.executePendingBindings()
        }
    }

}