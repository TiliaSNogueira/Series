package com.example.filmsandseries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private val showListAdapter: ShowListAdapter
) : Fragment(R.layout.fragment_show_list) {

    private lateinit var binding: FragmentShowListBinding
    lateinit var viewModel: ShowListViewModel

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

        viewModel.getShowList()
        setupRecyclerView()
        setupObservers()
        selectShow()
        setupButton()
        observeScroll()
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
                    //todo dialog de erro
                    Toast.makeText(context, it.message ?: "Error", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupButton() {
        binding.btnFavorites.setOnClickListener {
            findNavController().navigate(ShowListFragmentDirections.actionShowListFragmentToFavoritesShowsFragment())
        }
    }

    private fun observeScroll() {
        binding.imageRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.updatePage()
                    viewModel.getShowList()
                }
            }
        })
    }

}