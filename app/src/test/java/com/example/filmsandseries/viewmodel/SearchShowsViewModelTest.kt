package com.example.filmsandseries.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmsandseries.MainCoroutineRule
import com.example.filmsandseries.getOrAwaitValue
import com.example.filmsandseries.presentation.SearchShowViewModel
import com.example.filmsandseries.repository.FakeRepository
import com.example.filmsandseries.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchShowsViewModelTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchShowViewModel

    @Before
    fun setup() {
        viewModel = SearchShowViewModel(FakeRepository())
    }


    @Test
    fun `search show with blank query returns loading`() {
        val query = " "
        viewModel.searchShow(query)
        val value = viewModel.searchShows.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.LOADING)
    }

    @Test
    fun `search show with query returns success`() {
        val query = "nice"
        viewModel.searchShow(query)
        val value = viewModel.searchShows.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }

}