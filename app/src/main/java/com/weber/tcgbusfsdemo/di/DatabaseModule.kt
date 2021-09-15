package com.weber.tcgbusfsdemo.di

import android.content.Context
import androidx.room.Room
import com.weber.tcgbusfsdemo.database.AppDataBase
import com.weber.tcgbusfsdemo.database.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "Demo.db"
    ).build()

    @Provides
    @Singleton
    fun provideUsersDao(db: AppDataBase): UsersDao = db.usersDao()

}