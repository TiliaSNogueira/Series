package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("medium") val medium : String? = null,
    @SerializedName("original") val original : String? = null
)