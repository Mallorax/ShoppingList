package com.example.shoppinglist.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity

@Dao
interface ShoppingAppDao {

    @Query("SELECT * FROM shopping_lists WHERE status == 'current' ORDER BY shopping_lists.date DESC")
    fun getShoppingListAndGroceries(): DataSource.Factory<Int, ShoppingListEntity>

    @Query("SELECT * FROM shopping_lists WHERE status == 'archived' ORDER BY shopping_lists.date DESC")
    fun getArchivedListAndGroceries(): DataSource.Factory<Int, ShoppingListEntity>

    @Query("SELECT * FROM shopping_lists WHERE listId == :id")
    suspend fun getShoppingList(id: Long): ShoppingListEntity

    @Query("SELECT * FROM groceries WHERE listFkId == :listId ORDER BY groceryId DESC")
    fun getAllGroceriesForList(listId: Long): DataSource.Factory<Int, GroceryEntity>

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    @Query("DELETE FROM groceries WHERE groceryId == :id")
    suspend fun deleteGrocery(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrocery(grocery: GroceryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

}