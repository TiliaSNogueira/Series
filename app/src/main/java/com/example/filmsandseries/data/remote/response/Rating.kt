package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average") val average: Double? = null
)
