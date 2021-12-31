package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import com.example.shoppinglist.room.ShoppingAppDao
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class ShoppingApplication: Application() {


    override fun onCreate() {
        super.onCreate()
    }
}