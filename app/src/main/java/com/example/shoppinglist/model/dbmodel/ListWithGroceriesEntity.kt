package com.example.shoppinglist.model.dbmodel

import androidx.room.Embedded
import androidx.room.Relation


data class ListWithGroceriesEntity(
    @Embedded
    val shoppingListEntity: ShoppingListEntity,
    @Relation(
        parentColumn = "listId",
        entityColumn = "groceryId"
    )
    val groceriesList: List<GroceryEntity>
)
