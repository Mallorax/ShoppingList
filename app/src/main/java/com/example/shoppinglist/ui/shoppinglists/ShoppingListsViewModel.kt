package com.example.shoppinglist.ui.shoppinglists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(val repository: ShoppingListRepository): ViewModel() {

    val shoppingList = repository.loadShoppingList().cachedIn(viewModelScope)


    fun archiveShoppingList(date: Calendar, name: String, creation: Long){
        viewModelScope.launch(Dispatchers.IO){
            val shoppingList = ShoppingList(date, name, ShoppingListStatus.ARCHIVED, creation)
            repository.updateShoppingList(shoppingList)
        }
    }

}