package com.abora.nagwatask.retrofitDataModel

import com.google.gson.annotations.SerializedName

class MoviesDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String,
    @SerializedName("name") val name: String
)
