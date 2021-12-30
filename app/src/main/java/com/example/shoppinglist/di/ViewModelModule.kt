package com.example.shoppinglist.di

import com.example.shoppinglist.repository.ShoppingListRepository
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import com.example.shoppinglist.room.ShoppingAppDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun shoppingListRepo(repoImpl: ShoppingListRepositoryImpl): ShoppingListRepository

    @Inject
    @Singleton
    @ViewModelScoped
    fun provideShoppingListRepo(dao: ShoppingAppDao): ShoppingListRepository{
        return ShoppingListRepositoryImpl(dao)
    }


}