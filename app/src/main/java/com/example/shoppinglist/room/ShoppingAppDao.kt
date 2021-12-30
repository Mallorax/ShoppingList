package com.example.shoppinglist.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ListWithGroceriesEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity

@Dao
abstract class ShoppingAppDao {

    @Transaction
    @Query("SELECT * FROM shopping_lists ORDER BY shopping_lists.date DESC")
    abstract fun getShoppingListAndGroceries(): DataSource.Factory<Int, ListWithGroceriesEntity>

    suspend fun insertGroceriesForList(shoppingListWithGroceries: ListWithGroceriesEntity){
        val listOfGroceries = shoppingListWithGroceries.groceriesList
        val shoppingList = shoppingListWithGroceries.shoppingListEntity
        listOfGroceries.forEach {
            it.listFkId = shoppingList.listId
        }
        _insertShoppingList(shoppingList)
        _insertAllGroceries(listOfGroceries)
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun _insertAllGroceries(groceries: List<GroceryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun _insertShoppingList(shoppingList: ShoppingListEntity)

}