package com.weber.tcgbusfsdemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey
    val objectId: String,
    val username: String?,
    val code: String?,
    val isVerifiedReportEmail: String?,
    val reportEmail: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val parameter: Int?,
    val number: Int?,
    val phone: String?,
    val timeZone: String?,
    val sessionToken: String?
)
