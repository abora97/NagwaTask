package com.abora.nagwatask.retrofitApi

import com.abora.nagwatask.retrofitDataModel.MediaDataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface RetrofitApi {

    @GET("movies")
    fun getMoviesData() : Deferred<List<MediaDataModel>>


}