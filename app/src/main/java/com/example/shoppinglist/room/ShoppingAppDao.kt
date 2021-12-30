package com.example.shoppinglist.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppinglist.model.dbmodel.ListWithGroceriesEntity

@Dao
interface ShoppingAppDao {

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity")
    fun getShoppingListAndGroceries(): List<ListWithGroceriesEntity>
}