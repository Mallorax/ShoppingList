package com.example.shoppinglist.model.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "groceries",
    foreignKeys = [ForeignKey(
        entity = ShoppingListEntity::class,
        parentColumns = arrayOf("listId"),
        childColumns = arrayOf("listFkId"),
        onDelete = CASCADE
    )]
)
data class GroceryEntity(
    val name: String,
    val amount: Int,
    @ColumnInfo(index = true)
    var listFkId: Int = -1,
    @PrimaryKey(autoGenerate = true)
    val groceryId: Int = 0
)
