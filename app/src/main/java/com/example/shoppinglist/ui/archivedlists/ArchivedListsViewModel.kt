package com.example.shoppinglist.ui.archivedlists

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArchivedListsViewModel @Inject constructor(repo: ShoppingListRepository): ViewModel() {

    val archivedLists = repo.loadArchivedList()
}