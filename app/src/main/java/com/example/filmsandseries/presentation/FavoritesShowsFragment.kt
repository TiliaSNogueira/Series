package com.example.filmsandseries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandseries.R
import com.example.filmsandseries.databinding.FragmentFavoritesShowsBinding
import javax.inject.Inject

class FavoritesShowsFragment @Inject constructor(
    private val adapter: FavoritesShowsAdapter
) : Fragment(R.layout.fragment_favorites_shows) {

    private lateinit var binding: FragmentFavoritesShowsBinding
    lateinit var viewModel: FavoritesShowsViewModel

    private val swipeCallBalck =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedShow = adapter.favoritesShowList[layoutPosition]
                viewModel.deleteFavoriteShow(selectedShow)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FavoritesShowsViewModel::class.java]

        setupRecyclerView()
        setupObservers()
        setupNavigation()
        setupOnBackPressed()
        setupToolbarHomeButton()
    }

    private fun setupToolbarHomeButton() {
        binding.toolbarHome.setOnClickListener {
           findNavController().navigate(FavoritesShowsFragmentDirections.actionFavoritesShowsFragmentToShowListFragment())
        }
    }

    private fun setupRecyclerView() {
        binding.favoriteShowRecycler.adapter = adapter
        binding.favoriteShowRecycler.layoutManager = LinearLayoutManager(context)
        ItemTouchHelper(swipeCallBalck).attachToRecyclerView(binding.favoriteShowRecycler)
    }

    private fun setupObservers() {
        viewModel.favoritesShows.observe(viewLifecycleOwner, Observer {
            adapter.favoritesShowList = it
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun setupOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setupNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}