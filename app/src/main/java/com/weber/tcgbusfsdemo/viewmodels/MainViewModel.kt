package com.weber.tcgbusfsdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weber.tcgbusfsdemo.data.Users

class MainViewModel : ViewModel() {
    var mUser: Users? = null
    var mLoading: MutableLiveData<Int> = MutableLiveData()
    var mToolbarTitle: MutableLiveData<String> = MutableLiveData()
    var mShowBackArrow: MutableLiveData<Boolean> = MutableLiveData()
}