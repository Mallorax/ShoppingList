package com.example.shoppinglist.ui.shoppinglists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ShoppingListsItemBinding
import com.example.shoppinglist.databinding.ShoppingListsItemBindingImpl
import com.example.shoppinglist.model.appmodel.ShoppingList

class ShoppingListsAdapter(private val onItemClickListener: OnItemClickListener,
    private val onArchiveClickListener: OnArchiveClickListener):
    PagingDataAdapter<ShoppingList, ShoppingListsAdapter.ShoppingListVH>(DiffCallback){

    override fun onBindViewHolder(holder: ShoppingListVH, position: Int) {
        val shoppingList = getItem(position)
        holder.binding.root.setOnClickListener{
            onItemClickListener.onItemClick(shoppingList, it)
        }
        holder.binding.archiveImageView.setOnClickListener {
            onArchiveClickListener.onArchiveClick(shoppingList, it)
        }
        holder.bind(shoppingList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingListVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShoppingListsItemBindingImpl.inflate(inflater, parent, false)
        return ShoppingListVH(binding)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ShoppingList>(){
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.date === newItem.date && oldItem.listName === newItem.listName
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.date == newItem.date && oldItem.listName == newItem.listName
        }
    }

    class ShoppingListVH(val binding: ShoppingListsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(shoppingList: ShoppingList?){
            binding.shoppingList = shoppingList
            binding.executePendingBindings()
        }
    }

    class OnItemClickListener(val clickListener: (shoppingList: ShoppingList?, view: View) -> Unit){
        fun onItemClick(shoppingList: ShoppingList?, view: View) = clickListener(shoppingList, view)
    }

    class OnArchiveClickListener(val archiveClickListener: (shoppingList: ShoppingList?, view: View) -> Unit){
        fun onArchiveClick(shoppingList: ShoppingList?, view: View) = archiveClickListener(shoppingList, view)
    }
}