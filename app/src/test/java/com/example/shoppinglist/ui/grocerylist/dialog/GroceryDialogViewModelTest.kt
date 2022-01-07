package com.example.shoppinglist.ui.grocerylist.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GroceryDialogViewModelTest{

    private lateinit var viewModelTest: GroceryDialogViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Before
    fun setup(){
        viewModelTest = GroceryDialogViewModel(mockk())
    }

    @Test
    fun addGroceryErrorMessageTest(){
        viewModelTest.addGrocery("", "", 1L)
        assertNotNull(viewModelTest.errorMessage.value)
        assertNull(viewModelTest.dismissDialog.value)
    }
    @Test
    fun addGroceryTest(){
        viewModelTest.addGrocery("some name", "12", 3L)
        assertNull(viewModelTest.errorMessage.value)
        viewModelTest.dismissDialog.value?.let { assertTrue(it) }
    }
}