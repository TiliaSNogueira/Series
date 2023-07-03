package com.example.filmsandseries.data.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.filmsandseries.data.local.ShowDao
import com.example.filmsandseries.data.local.ShowDatabase
import com.example.filmsandseries.getOrAwaitValue
import com.example.filmsandseries.model.ShowDetails
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ShowDaoTest {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ShowDatabase

    private lateinit var dao: ShowDao


    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.showDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveFavoriteShowTest() = runBlocking {
        val fakeShow = ShowDetails(
            id = 10,
            image = "image",
            title = "Show Test",
            summary = "Summary test for show",
            genres = "Documentary",
            rating = 9.0
        )
        dao.insertShow(fakeShow)
        val favoritesList = dao.getFavoritesShowList().getOrAwaitValue()
        assertThat(favoritesList).contains(fakeShow)
    }

    @Test
    fun deleteFavoriteShowTest() = runBlocking {
        val fakeShow = ShowDetails(
            id = 10,
            image = "image",
            title = "Show Test",
            summary = "Summary test for show",
            genres = "Documentary",
            rating = 9.0
        )
        dao.insertShow(fakeShow)
        dao.deleteFavoriteShow(fakeShow)
        val favoritesList = dao.getFavoritesShowList().getOrAwaitValue()
        assertThat(favoritesList).doesNotContain(fakeShow)
    }

}