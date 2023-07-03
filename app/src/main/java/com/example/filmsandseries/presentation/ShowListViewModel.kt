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
class ShowListViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    var page = 1
    private val shows = MutableLiveData<ResultWrapper<List<ShowItem>>>()
    val showList: LiveData<ResultWrapper<List<ShowItem>>>
        get() = shows

    fun updatePage() {
        page += 1
        getShowList()
    }

    fun getShowList() {
        shows.value = ResultWrapper.loading(null)
        viewModelScope.launch {
            val response = repository.getShowList(page)
            shows.value = response
        }
    }

}