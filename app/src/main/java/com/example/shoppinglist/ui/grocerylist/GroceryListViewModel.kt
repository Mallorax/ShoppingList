package com.example.shoppinglist.ui.grocerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroceryListViewModel @Inject constructor(private val repositoryImpl: ShoppingListRepositoryImpl): ViewModel() {

    fun loadGroceries(shoppingListId: Long) = repositoryImpl.loadGroceryList(shoppingListId).cachedIn(viewModelScope)
}