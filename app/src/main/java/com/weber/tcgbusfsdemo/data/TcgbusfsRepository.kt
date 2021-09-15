package com.weber.tcgbusfsdemo.data

import com.weber.tcgbusfsdemo.network.TcgbusfsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TcgbusfsRepository @Inject constructor(
    private val tcgBusService: TcgbusfsService
) {
    fun getTcgbusfs(): Flow<Tcgbus?> {
        return flow {
            val tcgbus = tcgBusService.getTcgbus().body()
            emit(tcgbus)
        }.flowOn(Dispatchers.IO)
    }


}