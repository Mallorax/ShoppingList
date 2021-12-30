package com.example.shoppinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(val repository: ShoppingListRepository): ViewModel() {

    val shoppingList = repository.loadShoppingList().cachedIn(viewModelScope)

    fun saveShoppingList(shoppingList: ShoppingList){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertShoppingList(shoppingList)
        }
    }

}