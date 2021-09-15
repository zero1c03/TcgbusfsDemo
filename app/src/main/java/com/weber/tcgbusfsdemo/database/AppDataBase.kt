package com.weber.tcgbusfsdemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weber.tcgbusfsdemo.data.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}


