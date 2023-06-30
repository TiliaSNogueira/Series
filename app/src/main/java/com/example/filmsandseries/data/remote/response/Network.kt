package com.example.filmsandseries.data.remote.response

import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("country") val country: Country? = null,
    @SerializedName("officialSite") val officialSite: String? = null
)
