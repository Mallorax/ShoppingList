package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import com.example.shoppinglist.room.ShoppingAppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ShoppingListRepositoryImpl @Inject constructor(private val dao: ShoppingAppDao): ShoppingListRepository {


    override fun loadShoppingList(): Flow<PagingData<ShoppingList>> {
        val daoResponse = dao.getShoppingListAndGroceries().map { mapDbShoppingListToAppShoppingList(it) }
        return Pager(
            createPagingConfig(),
            pagingSourceFactory = daoResponse.asPagingSourceFactory(Dispatchers.IO)
        ).flow.flowOn(Dispatchers.IO)
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        dao.insertGroceriesForList(mapAppShoppingListToDbShoppingList(shoppingList))
    }

    private fun createPagingConfig(): PagingConfig{
        return PagingConfig(
            pageSize = 10,
            initialLoadSize = 20,
            prefetchDistance = 10,
            enablePlaceholders = false
        )
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        val shoppingListEntity = mapShoppingListToShoppingListEntity(shoppingList)
        dao.updateShoppingList(shoppingListEntity)
    }

    override suspend fun saveGrocery(grocery: Grocery) {
        dao.insertGrocery(mapAppGroceryToDbGrocery(grocery))
    }

    override suspend fun deleteGrocery(id: Long) {
        dao.deleteGrocery(id)
    }

    override fun loadGroceryList(shoppingListId: Long): Flow<PagingData<Grocery>> {
        val daoResponse = dao.getAllGroceriesForList(shoppingListId).map { mapDbGroceryToAppGrocery(it) }
        return Pager(
            createPagingConfig(),
            pagingSourceFactory = daoResponse.asPagingSourceFactory(Dispatchers.IO)
        ).flow.flowOn(Dispatchers.IO)
    }
}