package com.abora.nagwatask.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



open class BaseViewModel : ViewModel() {
//    lateinit var networkStatus: NetworkStatus
    val loading = MutableLiveData<Boolean>()
    val hideKeyboaard = MutableLiveData<Boolean>()
    val showMassage = MutableLiveData<String>()

}