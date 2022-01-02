package com.example.shoppinglist.repository

import com.example.shoppinglist.model.appmodel.Grocery
import com.example.shoppinglist.model.appmodel.ShoppingList
import com.example.shoppinglist.model.appmodel.ShoppingListStatus
import com.example.shoppinglist.model.dbmodel.GroceryEntity
import com.example.shoppinglist.model.dbmodel.ShoppingListEntity
import java.util.*


fun mapStringToShoppingListStatus(status: String): ShoppingListStatus {
    return when (status) {
        "archived" -> ShoppingListStatus.ARCHIVED
        else -> ShoppingListStatus.CURRENT
    }
}

fun mapShoppingListEntityToShoppingList(shoppingListEntity: ShoppingListEntity): ShoppingList {
    return ShoppingList(
        convertLongToCalendar(shoppingListEntity.date),
        shoppingListEntity.listName,
        mapStringToShoppingListStatus(shoppingListEntity.status),
        shoppingListEntity.listId
    )
}


fun mapShoppingListToShoppingListEntity(shoppingList: ShoppingList): ShoppingListEntity {
    return ShoppingListEntity(
        shoppingList.listName,
        shoppingList.date.timeInMillis,
        shoppingList.status.status,
        shoppingList.creation
    )
}

fun mapDbGroceryToAppGrocery(grocery: GroceryEntity): Grocery {
    return Grocery(
        grocery.name,
        grocery.amount,
        grocery.listFkId,
        grocery.groceryId
    )
}

fun mapAppGroceryToDbGrocery(grocery: Grocery): GroceryEntity {
    return GroceryEntity(
        grocery.name, grocery.amount,
        listFkId = grocery.shoppingListId,
        groceryId = grocery.groceryId
    )
}


fun convertLongToCalendar(time: Long): Calendar {
    val cal = Calendar.getInstance()
    cal.timeInMillis = time
    return cal
}