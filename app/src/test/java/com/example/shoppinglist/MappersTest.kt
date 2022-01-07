package com.example.shoppinglist

import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import com.example.shoppinglist.repository.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class MappersTest {

    @Test
    fun mapStringToShoppingListStatusTest(){
        val statusString = "archived"
        val status = mapStringToShoppingListStatus(statusString)
        assertThat(status.status, equalTo(ShoppingListStatus.ARCHIVED.status))
    }

    @Test
    fun mapShoppingListEntityToShoppingListTest(){
        val shoppingListEntity = ShoppingListEntity("test", 1L, "current", 1L)
        val shoppingList = mapShoppingListEntityToShoppingList(shoppingListEntity)
        assertThat(shoppingListEntity.listName, equalTo(shoppingList.listName))
        assertThat(shoppingListEntity.date, equalTo(shoppingList.date.timeInMillis))
        assertThat(mapStringToShoppingListStatus(shoppingListEntity.status), equalTo(shoppingList.status))
        assertThat(shoppingListEntity.listId, equalTo(shoppingList.creation))
    }

    @Test
    fun mapShoppingListToShoppingListEntityTest(){
        val shoppingList = ShoppingList(Calendar.getInstance(), "test", ShoppingListStatus.CURRENT, 1L)
        val shoppingListEntity = mapShoppingListToShoppingListEntity(shoppingList)
        assertThat(shoppingListEntity.listName, equalTo(shoppingList.listName))
        assertThat(shoppingListEntity.date, equalTo(shoppingList.date.timeInMillis))
        assertThat(mapStringToShoppingListStatus(shoppingListEntity.status), equalTo(shoppingList.status))
        assertThat(shoppingListEntity.listId, equalTo(shoppingList.creation))
    }

    @Test
    fun mapDbGroceryToAppGroceryTest(){
        val groceryEntity = GroceryEntity("name", 1, 1L, 1L)
        val grocery = mapDbGroceryToAppGrocery(groceryEntity)
        assertThat(groceryEntity.name, equalTo(grocery.name))
        assertThat(groceryEntity.amount, equalTo(grocery.amount))
        assertThat(groceryEntity.listFkId, equalTo(grocery.shoppingListId))
        assertThat(groceryEntity.groceryId, equalTo(grocery.groceryId))
    }

    @Test
    fun mapAppGroceryToDbGroceryTest(){
        val grocery = Grocery("test", 1, 1L, 1L)
        val groceryEntity = mapAppGroceryToDbGrocery(grocery)
        assertThat(groceryEntity.name, equalTo(grocery.name))
        assertThat(groceryEntity.amount, equalTo(grocery.amount))
        assertThat(groceryEntity.listFkId, equalTo(grocery.shoppingListId))
        assertThat(groceryEntity.groceryId, equalTo(grocery.groceryId))
    }

}