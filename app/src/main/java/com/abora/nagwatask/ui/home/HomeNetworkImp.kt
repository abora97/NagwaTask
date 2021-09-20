package com.abora.nagwatask.ui.home

import com.abora.nagwatask.base.NetworkException
import com.abora.nagwatask.base.NetworkResult
import com.abora.nagwatask.base.safeCall
import com.abora.nagwatask.retrofitApi.RetrofitApi
import com.abora.nagwatask.retrofitDataModel.MoviesDataModel
import javax.inject.Inject

class HomeNetworkImp @Inject constructor(): HomeNetwork {

    @Inject
    lateinit var retrofitApi: RetrofitApi

    override suspend fun getMoviesData(): NetworkResult<List<MoviesDataModel>> = safeCall {

        val response = retrofitApi.getMoviesData().await()

        if(response!=null){
            return@safeCall NetworkResult.Success(response)

        }else{
            return@safeCall NetworkResult.Error(NetworkException(""))
        }

    }


}