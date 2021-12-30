package com.example.shoppinglist.model.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryEntity(
    @PrimaryKey(autoGenerate = true)
    val groceryId: Int,
    val name: String,
    val amount: Int
)
