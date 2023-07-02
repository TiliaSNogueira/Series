package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchShowResponse(
    @SerializedName("score") val score: Double? = null,
    @SerializedName("show") val show: Show? = null
)