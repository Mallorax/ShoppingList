package com.example.shoppinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(repository: ShoppingListRepository): ViewModel() {

    val shoppingList = repository.loadShoppingList().cachedIn(viewModelScope)

}