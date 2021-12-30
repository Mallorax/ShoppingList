package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import com.example.shoppinglist.room.ShoppingAppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingListRepositoryImpl @Inject constructor(private val dao: ShoppingAppDao): ShoppingListRepository {


    override fun loadShoppingList(): LiveData<PagingData<ShoppingList>> {
        val daoResponse = dao.getShoppingListAndGroceries().map { mapDbShoppingListToAppShoppingList(it) }
        return Pager(
            createPagingConfig(),
            pagingSourceFactory = daoResponse.asPagingSourceFactory(Dispatchers.IO)
        ).liveData
    }

    private fun createPagingConfig(): PagingConfig{
        return PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            prefetchDistance = 10,
            enablePlaceholders = false
        )
    }
    
}