package com.abora.nagwatask.ui.home


import com.abora.nagwatask.base.NetworkResult
import com.abora.nagwatask.retrofitDataModel.MoviesDataModel
import com.google.gson.JsonObject

interface HomeNetwork {

    suspend fun getMoviesData() : NetworkResult<List<MoviesDataModel>>


}