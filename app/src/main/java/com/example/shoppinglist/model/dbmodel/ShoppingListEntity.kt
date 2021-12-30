package com.example.shoppinglist.model.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    val listId: Int,
    val listName: String,
    val date: Long,
    val status: String
)
