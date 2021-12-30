package com.example.shoppinglist.model.appmodel

import java.util.*

data class ShoppingList(
    val date: Calendar,
    val listName: String,
    val groceriesList: List<Grocery>,
    val status: ShoppingListStatus
)
