package com.example.shoppinglist.model.appmodel

import java.util.*

data class ShoppingList(
    val date: Calendar,
    val listName: String,
    var status: ShoppingListStatus,
    val creation: Long = Calendar.getInstance().timeInMillis
)
