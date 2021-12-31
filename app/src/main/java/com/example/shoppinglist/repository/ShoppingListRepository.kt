package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    fun loadShoppingList(): Flow<PagingData<ShoppingList>>
    suspend fun insertShoppingList(shoppingList: ShoppingList)
    fun loadGroceryList(soppingListId: Long): Flow<PagingData<Grocery>>
}