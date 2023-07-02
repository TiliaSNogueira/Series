package com.example.filmsandseries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandseries.data.RepositoryInterface
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private val details = MutableLiveData<ResultWrapper<ShowDetails>>()
    val showDetails: LiveData<ResultWrapper<ShowDetails>>
        get() = details

    fun getShowDetails(id: Int) {
        details.value = ResultWrapper.loading(null)
        viewModelScope.launch {
            val response = repository.getShowById(id)
            details.value = response
        }
    }

    fun saveFavoriteShow(show: ShowDetails) {
        viewModelScope.launch {
            repository.saveFavoriteShow(show)
        }
    }

}