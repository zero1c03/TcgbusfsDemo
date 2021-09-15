package com.weber.tcgbusfsdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.weber.tcgbusfsdemo.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TimeZoneViewModel @Inject constructor(
    private val timeZoneRepository: TimeZoneRepository,
    private val usersRepository: UsersRepository
) :
    ViewModel() {

    suspend fun getUserFromDB(user: String): Users {
        return usersRepository.getUser(user)
    }

    suspend fun updateUser(id: String?, token: String?, timezone: Timezone?): Flow<UpdateUser?> {
        return timeZoneRepository.updateUser(id, token, timezone)
    }

    fun getTimeZoneMenuList(): MutableMap<String, String> {
        val ids = TimeZone.getAvailableIDs()
        val timeZoneList = mutableMapOf<String, String>()
        for (id in ids) {
            timeZoneList[getTimeZoneMenu(TimeZone.getTimeZone(id))] =
                TimeUnit.MILLISECONDS.toHours(TimeZone.getTimeZone(id).rawOffset.toLong())
                    .toString()
        }
        return timeZoneList
    }

    private fun getTimeZoneMenu(tz: TimeZone): String {
        val hours: Long = TimeUnit.MILLISECONDS.toHours(tz.rawOffset.toLong())
        var result = "";
        result = if (hours > 0) {
            String.format("(GMT+%d) %s", hours, tz.getID());
        } else {
            String.format("(GMT%d) %s", hours, tz.getID());
        }

        return result
    }

}