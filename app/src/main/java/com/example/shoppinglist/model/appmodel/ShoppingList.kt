package com.example.shoppinglist.model.appmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ShoppingList(
    val date: Calendar,
    val listName: String,
    val groceriesList: List<Grocery>,
    val status: ShoppingListStatus
): Parcelable
