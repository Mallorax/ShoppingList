package com.example.shoppinglist.ui.grocerylist.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import com.example.shoppinglist.ui.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GroceryDialogViewModel @Inject constructor(private val repositoryImpl: ShoppingListRepositoryImpl): ViewModel() {

    val errorMessage = SingleLiveEvent<String>()
    val dismissDialog = SingleLiveEvent<Boolean>()

    fun addGrocery(name: String, quantity: String, shoppingListId: Long) {
        try {
            val grocery = Grocery(name, Integer.parseInt(quantity), shoppingListId, Calendar.getInstance().timeInMillis)
            saveGrocery(grocery)
            dismissDialog.value = true
        } catch (e: NumberFormatException) {
            errorMessage.value = "Put a number in quantity field"
        }
    }

    private fun saveGrocery(grocery: Grocery) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.saveGrocery(grocery)
        }
    }
}