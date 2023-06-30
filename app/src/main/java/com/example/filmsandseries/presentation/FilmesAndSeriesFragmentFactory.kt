package com.example.filmsandseries.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class FilmesAndSeriesFragmentFactory @Inject constructor(
    private val showListAdapter: ShowListAdapter,
    private val favoritesShowsAdapter: FavoritesShowsAdapter,
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ShowListFragment::class.java.name -> ShowListFragment(showListAdapter)
            ShowDetailsFragment::class.java.name -> ShowDetailsFragment(glide)
            FavoritesShowsFragment::class.java.name -> FavoritesShowsFragment(favoritesShowsAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}