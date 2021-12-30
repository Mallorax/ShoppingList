package com.example.shoppinglist.model.dbmodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    val listName: String,
    val date: Long,
    val status: String,
    @PrimaryKey(autoGenerate = true)
    val listId: Int = 0
)
