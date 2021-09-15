package com.weber.tcgbusfsdemo.network

import com.weber.tcgbusfsdemo.data.Timezone
import com.weber.tcgbusfsdemo.data.UpdateUser
import com.weber.tcgbusfsdemo.data.Users
import retrofit2.Response
import retrofit2.http.*

interface ParseplatformService {

    @Headers("X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @GET("/api/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<Users>


    @Headers(
        "X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD",
        "Content-Type: application/json"
    )
    @PUT("/api/users/{id}")
    suspend fun updateUser(
        @Path("id") objectId: String?,
        @Header("X-Parse-Session-Token") token: String?,
        @Body timezone: Timezone?
    ): Response<UpdateUser>
}