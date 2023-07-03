package com.example.filmsandseries.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.filmsandseries.R
import com.example.filmsandseries.getOrAwaitValue
import com.example.filmsandseries.launchFragmentInHiltContainer
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.presentation.FilmesAndSeriesFragmentFactory
import com.example.filmsandseries.presentation.ShowDetailsFragment
import com.example.filmsandseries.presentation.ShowDetailsFragmentArgs
import com.example.filmsandseries.presentation.ShowDetailsFragmentDirections
import com.example.filmsandseries.presentation.ShowDetailsViewModel
import com.example.filmsandseries.repository.FakeRepositoryTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShowDetailsFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: FilmesAndSeriesFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    private val args = ShowDetailsFragmentArgs(10)
    private val bundle = args.toBundle()

    private val navController: NavController = Mockito.mock(NavController::class.java)


    @Test
    fun testNavigationFromDetailsToSearch() {
        launchFragmentInHiltContainer<ShowDetailsFragment>(
            factory = fragmentFactory,
            fragmentArgs = bundle
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(withId(R.id.toolbar_search)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(
            ShowDetailsFragmentDirections.actionShowDetailsFragmentToSearchShowFragment()
        )
    }

    @Test
    fun testNavigationFromDetailsToFavorites() {
        launchFragmentInHiltContainer<ShowDetailsFragment>(
            factory = fragmentFactory,
            fragmentArgs = bundle
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(withId(R.id.toolbar_favorite)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(
            ShowDetailsFragmentDirections.actionShowDetailsFragmentToFavoritesShowsFragment()
        )
    }

    @Test
    fun testOnBackPressed() {
        launchFragmentInHiltContainer<ShowDetailsFragment>(
            factory = fragmentFactory,
            fragmentArgs = bundle
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.pressBack()
        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun testButtonSave() {
        val testViewModel = ShowDetailsViewModel(FakeRepositoryTest())
        var showTest : ShowDetails? = null
        launchFragmentInHiltContainer<ShowDetailsFragment>(
            factory = fragmentFactory,
            fragmentArgs = bundle,
        ) {
            Navigation.setViewNavController(requireView(), navController)
            viewModel = testViewModel
            this.show = ShowDetails(
                id = 10,
                image = "image",
                title = "Show Test",
                summary = "Summary test for show",
                genres = "Documentary",
                rating = 9.0
            )
            showTest= show as ShowDetails
        }
        Espresso.onView(withId(R.id.btn_save)).perform(ViewActions.click())
        assertThat(testViewModel.favorites.getOrAwaitValue()).contains(showTest)
    }

}