package com.weber.tcgbusfsdemo.data

import com.weber.tcgbusfsdemo.network.ParseplatformService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TimeZoneRepository @Inject constructor(private val parseplatformService: ParseplatformService) {
    fun updateUser(id: String?, token: String?, timezone: Timezone?): Flow<UpdateUser?> {
        return flow {
            val result = parseplatformService.updateUser(id, token, timezone).body()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}