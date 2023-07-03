package com.example.filmsandseries.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.example.filmsandseries.R
import com.example.filmsandseries.databinding.FragmentShowDetailsBinding
import com.example.filmsandseries.model.ShowDetails
import com.example.filmsandseries.util.Status
import javax.inject.Inject


class ShowDetailsFragment @Inject constructor(
    private var glide: RequestManager
) : Fragment(R.layout.fragment_show_details) {

    private lateinit var binding: FragmentShowDetailsBinding
    lateinit var viewModel: ShowDetailsViewModel
    var show: ShowDetails? = null
    private val errorDialog: ErrorDialog by lazy { ErrorDialog(this.requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ShowDetailsViewModel::class.java]

        val args: ShowDetailsFragmentArgs by navArgs()
        val id = args.showId

        loadScreen(id)
        setupObservers()
        setupSaveButton()
        setupOnBackPressed()
        setupNavigation()
        setupToolbarSearchButton()
        setupToolbarFavoriteButton()
    }

    private fun loadScreen(id: Int) {
        viewModel.getShowDetails(id)
    }

    private fun setupToolbarSearchButton() {
        binding.toolbarSearch.setOnClickListener {
            findNavController().navigate(ShowDetailsFragmentDirections.actionShowDetailsFragmentToSearchShowFragment())
        }
    }

    private fun setupToolbarFavoriteButton() {
        binding.toolbarFavorite.setOnClickListener {
            findNavController().navigate(ShowDetailsFragmentDirections.actionShowDetailsFragmentToFavoritesShowsFragment())
        }
    }

    private fun setupObservers() {
        viewModel.showDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    show = it.data
                    it.data?.let { it1 -> loadDetails(it1) }
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    errorDialog.show(it.message.toString())
                }
            }
        })
    }

    private fun loadDetails(data: ShowDetails) {
        with(data) {
            glide.load(this.image).into(binding.showDetailImage)
            binding.showDetailTitle.text = this.title
            binding.showDetailRating.text = this.rating.toString()
            binding.showDetailGenres.text = this.genres.toString()
            binding.showDetailSummary.text = this.summary
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            show?.let { it1 -> viewModel.saveFavoriteShow(it1) }
            Toast.makeText(context, "Salvo com sucesso", Toast.LENGTH_LONG).show()
            it.visibility = View.GONE
        }
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
            //findNavController().navigate(ShowDetailsFragmentDirections.actionShowDetailsFragmentToShowListFragment())
            findNavController().popBackStack()
        }
    }

}