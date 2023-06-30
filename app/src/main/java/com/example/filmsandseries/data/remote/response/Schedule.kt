package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("time") val time : String? = null,
    @SerializedName("days") val days : List<String?>? = listOf()
)
