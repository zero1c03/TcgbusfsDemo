package com.weber.tcgbusfsdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.weber.tcgbusfsdemo.data.LoginRepository
import com.weber.tcgbusfsdemo.data.Users
import com.weber.tcgbusfsdemo.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    fun getUserInfo(id: String, password: String): Flow<Users?> {
        return loginRepository.getUserInfo(id, password)
    }

    suspend fun setUserInfo(users: Users) {
        usersRepository.setUsers(users)
    }
}