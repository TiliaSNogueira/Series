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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmsandseries.R
import com.example.filmsandseries.databinding.FragmentSearchShowBinding
import com.example.filmsandseries.model.ShowItem
import com.example.filmsandseries.util.Status
import javax.inject.Inject


class SearchShowFragment @Inject constructor(
    private val adapter: SearchShowAdapter
) : Fragment(R.layout.fragment_search_show) {

    private lateinit var binding: FragmentSearchShowBinding
    lateinit var viewModel: SearchShowViewModel
    private val errorDialog: ErrorDialog by lazy { ErrorDialog(this.requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SearchShowViewModel::class.java]

        setupSearchView()
        setupRecyclerView()
        setupObservers()
        selectShow()
        setupOnBackPressed()
        setupNavigation()
        setupToolbarHomeButton()
        setupToolbarFavoritesButton()
    }

    private fun setupToolbarHomeButton() {
        binding.toolbarHome.setOnClickListener {
            findNavController().navigate(SearchShowFragmentDirections.actionSearchShowFragmentToShowListFragment())
        }
    }

    private fun setupToolbarFavoritesButton() {
        binding.toolbarFavorite.setOnClickListener {
            findNavController().navigate(SearchShowFragmentDirections.actionSearchShowFragmentToFavoritesShowsFragment())
        }
    }

    private fun selectShow() {
        adapter.setOnShowClickListener { showId ->
            val action =
                ShowListFragmentDirections.actionShowListFragmentToShowDetailsFragment(showId)
            findNavController().navigate(action)
        }
    }

    private fun setupObservers() {
        viewModel.searchShows.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.let { it1 -> adapter.setData(it1 as MutableList<ShowItem>) }
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    errorDialog.show(it.message.toString())
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun setupSearchView() {
        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    viewModel.searchShow(query)
                } else {
                    adapter.clear()
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isNotEmpty()) {
                    viewModel.searchShow(query)
                } else {
                    adapter.clear()
                }
                return false
            }
        })
    }

    private fun setupOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                adapter.clear()
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setupNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            adapter.clear()
        }
    }

}