package com.example.filmsandseries.data.remote

import com.example.filmsandseries.data.remote.response.SearchShowResponse
import com.example.filmsandseries.data.remote.response.ShowDetailsResponse
import com.example.filmsandseries.data.remote.response.ShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsAndSeriesAPI {

    @GET("shows")
    suspend fun getShowsFromAPI(@Query("page") page: Int): Response<List<ShowResponse>>

    @GET("shows/{id}")
    suspend fun getShowDetailsFromAPI(@Path("id") showId: Int): Response<ShowDetailsResponse>

    @GET("search/shows")
    suspend fun searchShowFromAPI(@Query("q") name: String): Response<List<SearchShowResponse>>

}