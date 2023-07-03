package com.example.filmsandseries.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.filmsandseries.R
import com.example.filmsandseries.launchFragmentInHiltContainer
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.presentation.FavoritesShowsFragment
import com.example.filmsandseries.presentation.FilmesAndSeriesFragmentFactory
import com.example.filmsandseries.presentation.SearchShowAdapter
import com.example.filmsandseries.presentation.SearchShowFragment
import com.example.filmsandseries.presentation.SearchShowFragmentDirections
import com.example.filmsandseries.presentation.SearchShowViewModel
import com.example.filmsandseries.repository.FakeRepositoryTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class SearchShowFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: FilmesAndSeriesFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    private val navController: NavController = Mockito.mock(NavController::class.java)


    @Test
    fun testNavigationFromSearchToHome() {
        launchFragmentInHiltContainer<SearchShowFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_home)).perform(click())
        Mockito.verify(navController).navigate(
            SearchShowFragmentDirections.actionSearchShowFragmentToShowListFragment()
        )
    }

    @Test
    fun testNavigationFromShowListToFavorites() {
        launchFragmentInHiltContainer<SearchShowFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_favorite)).perform(click())
        Mockito.verify(navController).navigate(
            SearchShowFragmentDirections.actionSearchShowFragmentToFavoritesShowsFragment()
        )
    }

    @Test
    fun testOnBackPressed() {
        launchFragmentInHiltContainer<FavoritesShowsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.pressBack()
        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun testNavigationFromSearchToShowDetails() {
        val testViewModel = SearchShowViewModel(FakeRepositoryTest())
        launchFragmentInHiltContainer<SearchShowFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
            viewModel = testViewModel
            adapter.searchShowList = mutableListOf(
                ShowItem(0, "image url", "title test")
            )
        }
        Espresso.onView(ViewMatchers.withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchShowAdapter.ShowViewHolder>(
                0, click()
            )
        )
        Mockito.verify(navController).navigate(
            SearchShowFragmentDirections.actionSearchShowFragmentToShowDetailsFragment(0)
        )
    }

}