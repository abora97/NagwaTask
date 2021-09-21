package com.abora.nagwatask.ui.home


import com.abora.nagwatask.base.NetworkResult
import com.abora.nagwatask.retrofitDataModel.MediaDataModel

interface HomeNetwork {

    suspend fun getMoviesData() : NetworkResult<List<MediaDataModel>>


}