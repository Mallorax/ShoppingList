package com.example.shoppinglist.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ListWithGroceriesEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity

@Dao
abstract class ShoppingAppDao {

    @Transaction
    @Query("SELECT * FROM shopping_lists ORDER BY shopping_lists.date")
    abstract fun getShoppingListAndGroceries(): DataSource.Factory<Int, ListWithGroceriesEntity>

    fun insertGroceriesForList(shoppingList: ShoppingListEntity, groceries: List<GroceryEntity>){
        groceries.forEach {
            it.listFkId = shoppingList.listId
        }
        _insertShoppingList(shoppingList)
        _insertAllGroceries(groceries)
    }
    @Insert
    abstract fun _insertAllGroceries(groceries: List<GroceryEntity>)
    
    @Insert
    abstract fun _insertShoppingList(shoppingList: ShoppingListEntity)

}