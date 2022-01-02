package com.example.shoppinglist.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity

@Dao
abstract class ShoppingAppDao {

    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE status == 'current' ORDER BY shopping_lists.date DESC")
    abstract fun getShoppingListAndGroceries(): DataSource.Factory<Int, ShoppingListEntity>

    @Transaction
    @Query("SELECT * FROM shopping_lists WHERE status == 'archived' ORDER BY shopping_lists.date DESC")
    abstract fun getArchivedListAndGroceries(): DataSource.Factory<Int, ShoppingListEntity>

    @Query("SELECT * FROM shopping_lists WHERE listId == :id")
    abstract suspend fun getShoppingList(id: Long): ShoppingListEntity



    @Query("SELECT * FROM groceries WHERE listFkId == :listId ORDER BY groceryId DESC")
    abstract fun getAllGroceriesForList(listId: Long): DataSource.Factory<Int, GroceryEntity>


    @Update
    abstract suspend fun updateShoppingList(shoppingList: ShoppingListEntity)

    @Query("DELETE FROM groceries WHERE groceryId == :id")
    abstract suspend fun deleteGrocery(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGrocery(grocery: GroceryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun _insertAllGroceries(groceries: List<GroceryEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertShoppingList(shoppingList: ShoppingListEntity)

}