package com.example.shoppinglist.model.appmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Grocery(
    val name: String,
    val amount: Int,
    val shoppingListId: Long
)
