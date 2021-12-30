package com.example.shoppinglist.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppinglist.model.dbmodel.ListWithGroceriesEntity

@Dao
interface ShoppingAppDao {

    @Transaction
    @Query("SELECT * FROM shopping_lists ORDER BY shopping_lists.date")
    fun getShoppingListAndGroceries(): DataSource.Factory<Int, ListWithGroceriesEntity>
}