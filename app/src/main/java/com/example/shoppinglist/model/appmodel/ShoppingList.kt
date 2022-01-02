package com.example.shoppinglist.model.appmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

data class ShoppingList(
    val date: Calendar,
    val listName: String,
    var status: ShoppingListStatus,
    val creation: Long = Calendar.getInstance().timeInMillis
)
