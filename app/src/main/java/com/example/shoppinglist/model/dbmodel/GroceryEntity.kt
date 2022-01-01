package com.example.shoppinglist.model.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "groceries",
    foreignKeys = [ForeignKey(
        entity = ShoppingListEntity::class,
        parentColumns = arrayOf("listId"),
        childColumns = arrayOf("listFkId"),
        onDelete = CASCADE
    )],
    primaryKeys = ["groceryId"]
)
data class GroceryEntity(
    val name: String,
    val amount: Int,
    @ColumnInfo(index = true)
    var listFkId: Long = -1,
    val groceryId: Long
)
