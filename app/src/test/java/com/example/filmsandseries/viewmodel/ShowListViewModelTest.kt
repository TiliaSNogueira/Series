package com.example.filmsandseries.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.filmsandseries.MainCoroutineRule
import com.example.filmsandseries.getOrAwaitValue
import com.example.filmsandseries.presentation.ShowListViewModel
import com.example.filmsandseries.repository.FakeRepository
import com.example.filmsandseries.util.Status
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShowListViewModelTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShowListViewModel

    @Before
    fun setup(){
        viewModel = ShowListViewModel(FakeRepository())
    }

    @Test
    fun `get more show with pagination`() {
        viewModel.updatePage()
        val value = viewModel.showList.getOrAwaitValue()
        Truth.assertThat(value.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `increment pagination plus one`() {
        viewModel.updatePage()
        val pageTest = viewModel.page
        Truth.assertThat(pageTest).isEqualTo(2)
    }

}