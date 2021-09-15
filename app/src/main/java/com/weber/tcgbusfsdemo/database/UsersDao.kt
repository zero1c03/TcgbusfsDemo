package com.weber.tcgbusfsdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weber.tcgbusfsdemo.data.Users

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users WHERE username=:username")
    fun getUser(username: String): Users

    @Query("SELECT sessionToken FROM Users WHERE username=:username")
    fun getUserToken(username: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: Users)
}