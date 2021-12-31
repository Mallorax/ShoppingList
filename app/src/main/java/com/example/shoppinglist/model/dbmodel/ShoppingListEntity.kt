package com.example.shoppinglist.model.dbmodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shopping_lists",
    primaryKeys = ["listId"])
data class ShoppingListEntity(
    val listName: String,
    val date: Long,
    val status: String,
    val listId: Long = Calendar.getInstance().timeInMillis
)
