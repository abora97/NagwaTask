package com.abora.nagwatask.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abora.nagwatask.base.NetworkException
import com.abora.nagwatask.base.NetworkResult
import com.abora.nagwatask.retrofitDataModel.MediaDataModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModelImp @Inject constructor() : ViewModel() , HomeViewModel {

    val progressStatus = MutableLiveData<Boolean>()
    val showAlert = MutableLiveData<String>()
    val moviesDataLoaded = MutableLiveData<List<MediaDataModel>>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var homeNetworkImp: HomeNetworkImp


    override fun getMoviesData() {
        progressStatus.value = true

        viewModelScope.launch {

            when(val res = homeNetworkImp.getMoviesData()){
                is NetworkResult.Success -> {
                    progressStatus.postValue(false)
                    res.responseData.apply {

                        moviesDataLoaded.value =this

                    }
                }
                is NetworkResult.Error -> {
                    progressStatus.postValue(false)
                    if(res.exception is NetworkException && !TextUtils.isEmpty(res.exception.message)){
                        showAlert.value = res.exception.message
                    }
                }
            }

        }

    }


}