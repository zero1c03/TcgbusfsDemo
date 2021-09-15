package com.weber.tcgbusfsdemo.network

import com.weber.tcgbusfsdemo.data.Tcgbus
import retrofit2.Response
import retrofit2.http.GET

interface TcgbusfsService {

    @GET("/dotapp/news.json")
    suspend fun getTcgbus(): Response<Tcgbus>
}