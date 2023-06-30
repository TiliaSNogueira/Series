package com.example.filmsandseries.data

import androidx.lifecycle.LiveData
import com.example.filmsandseries.data.local.ShowDao
import com.example.filmsandseries.data.remote.FilmsAndSeriesAPI
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.util.ResultWrapper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: FilmsAndSeriesAPI,
    private val dao: ShowDao
) : RepositoryInterface {

    override suspend fun getShowList(page: Int): ResultWrapper<List<ShowItem>> {
        return try {
            val response = api.getShowsFromAPI(page)

            if (response.isSuccessful) {
                response.body()?.let { it ->
                    val showList = it.map {
                        ShowItem(
                            idItem = it.id,
                            imageItem = it.image?.medium,
                            titleItem = it.name
                        )
                    }
                    return@let ResultWrapper.succcess(showList)
                } ?: ResultWrapper.error("Error", null)
            } else {
                ResultWrapper.error("Error", null)
            }
        } catch (e: Exception) {
            ResultWrapper.error("Error", null)
        }
    }

    override suspend fun getShowById(id: Int): ResultWrapper<ShowDetails> {
        return try {
            val response = api.getShowDetailsFromAPI(id)

            if (response.isSuccessful) {
                response.body()?.let { it ->
                    val details = ShowDetails(
                        id = it.id,
                        image = it.image.medium,
                        title = it.name,
                        summary = it.summary,
                        genres = it.genres,
                        rating = it.rating?.average
                    )
                    return@let ResultWrapper.succcess(details)
                } ?: ResultWrapper.error("Error", null)
            } else {
                ResultWrapper.error("Error", null)
            }
        } catch (e: Exception) {
            ResultWrapper.error("Error", null)
        }
    }

    override fun getFavoritesShow(): LiveData<List<ShowDetails>> {
       return dao.getFavoritesShowList()
    }

    override suspend fun saveFavoriteShow(show: ShowDetails) {
        dao.insertShow(show)
    }

    override suspend fun deleteFavoriteShow(show: ShowDetails) {
        dao.deleteFavoriteShow(show)
    }

}