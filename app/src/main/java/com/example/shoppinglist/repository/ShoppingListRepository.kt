package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.shoppinglist.model.appmodel.ShoppingList

interface ShoppingListRepository {

    fun loadShoppingList(): LiveData<PagingData<ShoppingList>>
}