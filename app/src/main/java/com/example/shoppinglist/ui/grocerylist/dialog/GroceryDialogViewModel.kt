package com.example.shoppinglist.ui.grocerylist.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GroceryDialogViewModel @Inject constructor(private val repositoryImpl: ShoppingListRepositoryImpl): ViewModel() {

    private val _errorMessage = LiveEvent<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _dismissDialog = LiveEvent<Boolean>()
    val dismissDialog: LiveData<Boolean> get() = _dismissDialog

    fun addGrocery(name: String, quantity: String, shoppingListId: Long) {
        try {
            val grocery = Grocery(name, Integer.parseInt(quantity), shoppingListId, Calendar.getInstance().timeInMillis)
            saveGrocery(grocery)
            _dismissDialog.value = true
        } catch (e: NumberFormatException) {
            _errorMessage.value = "Put a number in quantity field"
        }
    }

    private fun saveGrocery(grocery: Grocery) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.saveGrocery(grocery)
        }
    }
}