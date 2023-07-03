package com.example.filmsandseries.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsandseries.data.RepositoryInterface
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchShowViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private val search = MutableLiveData<ResultWrapper<List<ShowItem>>>()
    val searchShows: LiveData<ResultWrapper<List<ShowItem>>>
        get() = search

    fun searchShow(name: String) {
        search.value = ResultWrapper.loading(null)
        if (name.isNotBlank()) {
            viewModelScope.launch {
                val response = repository.searchShow(name)
                search.value = response
            }
        } else {
            search.value = ResultWrapper.loading(null)
        }
    }
}
