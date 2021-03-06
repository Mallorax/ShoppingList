package com.example.shoppinglist.ui.grocerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryListViewModel @Inject constructor(private val repositoryImpl: ShoppingListRepositoryImpl) :
    ViewModel() {

    fun loadGroceries(shoppingListId: Long): Flow<PagingData<Grocery>> {
        return repositoryImpl.loadGroceryList(shoppingListId).cachedIn(viewModelScope)
    }


    fun deleteGrocery(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteGrocery(id)
        }
    }

    fun isListArchived(id: Long) = liveData {
        val result: Boolean = when (repositoryImpl.getShoppingListStatus(id)) {
            ShoppingListStatus.ARCHIVED -> true
            else -> false
        }
        emit(result)
    }

}