package com.example.shoppinglist.ui.shoppinglists.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShoppingListDialogViewModel @Inject constructor(private val repo: ShoppingListRepository): ViewModel() {

    fun saveShoppingList(date: Calendar, name: String){
        val shoppingList = ShoppingList(date, name, ShoppingListStatus.CURRENT, Calendar.getInstance().timeInMillis)
        viewModelScope.launch(Dispatchers.IO){
            repo.insertShoppingList(shoppingList)
        }
    }
}