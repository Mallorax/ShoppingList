package com.example.shoppinglist.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity

@Database(entities = [GroceryEntity::class, ShoppingListEntity::class], version = 5)
abstract class ShoppingAppDb: RoomDatabase() {
    abstract fun shoppingAppDao(): ShoppingAppDao
}