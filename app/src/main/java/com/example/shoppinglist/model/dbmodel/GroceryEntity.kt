package com.example.shoppinglist.model.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groceries")
data class GroceryEntity(
    @PrimaryKey(autoGenerate = true)
    val groceryId: Int,
    val name: String,
    val amount: Int
)
