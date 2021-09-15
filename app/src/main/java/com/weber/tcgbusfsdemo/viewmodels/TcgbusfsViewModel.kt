package com.weber.tcgbusfsdemo.viewmodels

import androidx.lifecycle.ViewModel
import com.weber.tcgbusfsdemo.data.Tcgbus
import com.weber.tcgbusfsdemo.data.TcgbusfsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TcgbusfsViewModel @Inject constructor(private val tcgbusfsRepository: TcgbusfsRepository) :
    ViewModel() {
    fun getTcgbusfs(): Flow<Tcgbus?> {
        return tcgbusfsRepository.getTcgbusfs()
    }
}