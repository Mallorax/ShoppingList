package com.example.shoppinglist.model.appmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Grocery(
    val name: String,
    val amount: Int
):Parcelable
