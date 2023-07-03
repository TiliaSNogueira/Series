package com.example.filmsandseries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsandseries.R
import com.example.filmsandseries.databinding.FragmentShowListBinding
import com.example.filmsandseries.util.Status
import javax.inject.Inject


class ShowListFragment @Inject constructor(
    val showListAdapter: ShowListAdapter
) : Fragment(R.layout.fragment_show_list) {

    private lateinit var binding: FragmentShowListBinding
    lateinit var viewModel: ShowListViewModel
    private val errorDialog: ErrorDialog by lazy { ErrorDialog(this.requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ShowListViewModel::class.java]

        loadScreen()
        setupRecyclerView()
        setupObservers()
        selectShow()
        observeScroll()
        setupToolbarSearchButton()
        setupToolbarFavoriteButton()
    }

    private fun loadScreen() {
        viewModel.getShowList()
    }

    private fun setupToolbarSearchButton() {
        binding.toolbarSearch.setOnClickListener {
            findNavController().navigate(ShowListFragmentDirections.actionShowListFragmentToSearchShowFragment())
        }
    }

    private fun setupToolbarFavoriteButton() {
        binding.toolbarFavorite.setOnClickListener {
            findNavController().navigate(ShowListFragmentDirections.actionShowListFragmentToFavoritesShowsFragment())
        }
    }

    private fun selectShow() {
        showListAdapter.setOnShowClickListener { showId ->
            val action =
                ShowListFragmentDirections.actionShowListFragmentToShowDetailsFragment(showId)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() {
        binding.imageRecyclerView.adapter = showListAdapter
        binding.imageRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun setupObservers() {
        viewModel.showList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    it.data?.let { it1 -> showListAdapter.setData(it1) }
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    errorDialog.show(it.message.toString())
                }
            }
        })
    }

    private fun observeScroll() {
        binding.imageRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.updatePage()
                }
            }
        })
    }

}