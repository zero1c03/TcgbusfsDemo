package com.weber.tcgbusfsdemo.data

import com.weber.tcgbusfsdemo.database.UsersDao
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersDao: UsersDao) {

    suspend fun setUsers(users: Users) = usersDao.insertAll(users)
    suspend fun getUser(userName: String): Users = usersDao.getUser(userName)

}