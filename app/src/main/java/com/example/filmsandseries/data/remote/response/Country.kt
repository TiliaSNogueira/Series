package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name : String? = null,
    @SerializedName("code") val code : String? = null,
    @SerializedName("timezone") val timezone : String? = null
)
