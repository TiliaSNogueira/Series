package com.example.filmsandseries.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.filmsandseries.R
import com.example.filmsandseries.launchFragmentInHiltContainer
import com.example.filmsandseries.presentation.FilmesAndSeriesFragmentFactory
import com.example.filmsandseries.presentation.ShowListFragment
import com.example.filmsandseries.presentation.ShowListFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class ShowListFragmentTest {

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
    fun testNavigationFromShowListToShowDetails() {
//        launchFragmentInHiltContainer<ShowListFragment>(
//            factory = fragmentFactory
//        ) {
//            Navigation.setViewNavController(requireView(), navController)
//        }
//        Espresso.onView(ViewMatchers.withId(R.id.item_recycler)).perform(click())
//        Mockito.verify(navController).navigate(
//            ShowListFragmentDirections.actionShowListFragmentToShowDetailsFragment(1)
//        )
    }

    @Test
    fun testNavigationFromShowListToSearch() {
        launchFragmentInHiltContainer<ShowListFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_search)).perform(click())
        Mockito.verify(navController).navigate(
            ShowListFragmentDirections.actionShowListFragmentToSearchShowFragment()
        )
    }

    @Test
    fun testNavigationFromShowListToFavorites() {
        launchFragmentInHiltContainer<ShowListFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_favorite)).perform(click())
        Mockito.verify(navController).navigate(
            ShowListFragmentDirections.actionShowListFragmentToFavoritesShowsFragment()
        )
    }

}