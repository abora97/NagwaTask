package com.abora.nagwatask.retrofitApi

import com.abora.nagwatask.retrofitDataModel.MoviesDataModel
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface RetrofitApi {

    @GET("movies")
    fun getMoviesData() : Deferred<List<MoviesDataModel>>


}