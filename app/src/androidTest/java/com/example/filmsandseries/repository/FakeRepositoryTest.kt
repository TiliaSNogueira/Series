package com.example.filmsandseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.filmsandseries.data.RepositoryInterface
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.util.ResultWrapper

class FakeRepositoryTest : RepositoryInterface {

    private val favorites = mutableListOf<ShowDetails>()
    private val favoriteLiveData = MutableLiveData<List<ShowDetails>>(favorites)


    override suspend fun getShowList(page: Int): ResultWrapper<List<ShowItem>> {
        return ResultWrapper.succcess(listOf())
    }

    override suspend fun getShowById(id: Int): ResultWrapper<ShowDetails> {
        return ResultWrapper.succcess(ShowDetails(null, "", "", "", "", 0.0))
    }

    override fun getFavoritesShow(): LiveData<List<ShowDetails>> {
        return favoriteLiveData
    }

    override suspend fun saveFavoriteShow(show: ShowDetails) {
        favorites.add(show)
    }

    override suspend fun deleteFavoriteShow(show: ShowDetails) {
        favorites.remove(show)
        refreshData()
    }

    override suspend fun searchShow(name: String): ResultWrapper<List<ShowItem>> {
        return ResultWrapper.succcess(listOf())
    }

    private fun refreshData() {
        favoriteLiveData.postValue(favorites)
    }

}