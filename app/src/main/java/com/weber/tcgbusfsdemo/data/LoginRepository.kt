package com.weber.tcgbusfsdemo.data

import com.weber.tcgbusfsdemo.network.ParseplatformService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: ParseplatformService) {
    fun getUserInfo(id: String, password: String): Flow<Users?> {
        return flow {
            val users: Users? = service.login(id, password).body()
            emit(users)
        }.flowOn(Dispatchers.IO)
    }
}