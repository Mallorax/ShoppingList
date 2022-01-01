package com.example.shoppinglist.ui.archivedlists


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ArchivedListsItemBinding
import com.example.shoppinglist.model.appmodel.ShoppingList

class ArchivedListsAdapter(private val onItemClickListener: OnItemClickListener):
    PagingDataAdapter<ShoppingList, ArchivedListsAdapter.ArchivedListVH>(DiffCallback){

    override fun onBindViewHolder(holder: ArchivedListVH, position: Int) {
        val archivedList = getItem(position)
        holder.binding.root.setOnClickListener{
            onItemClickListener.onItemClick(archivedList, it)
        }
        holder.bind(archivedList)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchivedListVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArchivedListsItemBinding.inflate(inflater, parent, false)
        return ArchivedListVH(binding)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ShoppingList>(){
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.date === newItem.date && oldItem.listName === newItem.listName
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.date == newItem.date && oldItem.listName == newItem.listName
        }
    }

    class ArchivedListVH(val binding: ArchivedListsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(archivedList: ShoppingList?){
            binding.archivedList = archivedList
            binding.executePendingBindings()
        }
    }

    class OnItemClickListener(val clickListener: (shoppingList: ShoppingList?, view: View) -> Unit){
        fun onItemClick(archivedList: ShoppingList?, view: View) = clickListener(archivedList, view)
    }

}