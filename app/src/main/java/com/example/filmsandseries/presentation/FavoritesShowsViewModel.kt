package com.example.filmsandseries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandseries.data.RepositoryInterface
import com.example.filmsandseries.model.ShowDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesShowsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val favoritesShows = repository.getFavoritesShow()

    fun deleteFavoriteShow(show: ShowDetails) {
        viewModelScope.launch {
            repository.deleteFavoriteShow(show)
        }
    }

}