package com.example.shoppinglist.ui.grocerylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify


class GroceryListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModelTest: GroceryListViewModel
    private val repo = mockk<ShoppingListRepositoryImpl>()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModelTest = GroceryListViewModel(repo)
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun listIsArchivedTest() {
        every {
            runBlocking { repo.getShoppingListStatus(1L) }
        } returns ShoppingListStatus.ARCHIVED
        viewModelTest.isListArchived(1L).value?.let { assertFalse(it) }
    }

    @Test
    fun listIsCurrentTest(){
        every {
            runBlocking { repo.getShoppingListStatus(1L) }
        } returns ShoppingListStatus.CURRENT
        viewModelTest.isListArchived(1L).value?.let { assertTrue(it) }
    }
}