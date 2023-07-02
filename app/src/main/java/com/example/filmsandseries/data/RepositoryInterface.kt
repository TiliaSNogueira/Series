package com.example.filmsandseries.data

import androidx.lifecycle.LiveData
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.util.ResultWrapper

interface RepositoryInterface {

    suspend fun getShowList(page: Int): ResultWrapper<List<ShowItem>>

    suspend fun getShowById(id: Int): ResultWrapper<ShowDetails>

    fun getFavoritesShow(): LiveData<List<ShowDetails>>

    suspend fun saveFavoriteShow(show: ShowDetails)

    suspend fun deleteFavoriteShow(show: ShowDetails)

    suspend fun searchShow(name: String): ResultWrapper<List<ShowItem>>
}