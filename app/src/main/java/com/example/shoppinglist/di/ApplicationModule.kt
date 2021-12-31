package com.example.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.example.shoppinglist.repository.ShoppingListRepositoryImpl
import com.example.shoppinglist.room.ShoppingAppDao
import com.example.shoppinglist.room.ShoppingAppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

        @Provides
        @Singleton
        fun provideShoppingAppDb(@ApplicationContext appContext: Context): ShoppingAppDb{
            return Room.databaseBuilder(
                appContext,
                ShoppingAppDb::class.java,
                "ShoppingAppDb.db"
            )
                .fallbackToDestructiveMigration().build()
        }

    @Provides
    @Singleton
    fun provideShoppingAppDao(appDb: ShoppingAppDb): ShoppingAppDao{
        return appDb.shoppingAppDao()
    }

}