package com.example.shoppinglist.model.appmodel

data class Grocery(
    val name: String,
    val amount: Int,
    val shoppingListId: Long,
    val groceryId: Long
)
