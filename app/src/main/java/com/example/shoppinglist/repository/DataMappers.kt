package com.example.shoppinglist.repository

import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ListWithGroceriesEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import java.util.*

fun mapDbShoppingListToAppShoppingList(shoppingListDb: ListWithGroceriesEntity): ShoppingList {
    val date = convertLongToCalendar(shoppingListDb.shoppingListEntity.date)
    val groceries = shoppingListDb.groceriesList.map { t -> mapDbGroceryToAppGrocery(t) }
    val status = when(shoppingListDb.shoppingListEntity.status){
        "archived" -> ShoppingListStatus.ARCHIVED
        else -> ShoppingListStatus.CURRENT
    }
    return ShoppingList(date,
        shoppingListDb.shoppingListEntity.listName,
        groceries,
        status)
}

fun mapAppShoppingListToDbShoppingList(shoppingList: ShoppingList): ListWithGroceriesEntity{
    val shoppingListDb = ShoppingListEntity(shoppingList.listName, shoppingList.date.timeInMillis, shoppingList.status.status)
    val groceriesListDb = shoppingList.groceriesList.map {  mapAppGroceryToDbGrocery(it)}
    return ListWithGroceriesEntity(shoppingListDb, groceriesListDb)
}

private fun mapDbGroceryToAppGrocery(grocery: GroceryEntity): Grocery {
    return Grocery(
        grocery.name,
        grocery.amount
    )
}

private fun mapAppGroceryToDbGrocery(grocery: Grocery): GroceryEntity{
    return GroceryEntity(grocery.name, grocery.amount)
}


fun convertLongToCalendar(time: Long): Calendar{
    val cal = Calendar.getInstance()
    cal.timeInMillis = time
    return cal
}