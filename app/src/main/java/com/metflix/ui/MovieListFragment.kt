package com.metflix.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.metflix.R
import com.metflix.adapter.MovieAdapter
import com.metflix.common.BindingFragment
import com.metflix.adapter.MovieLoadStateAdapter
import com.metflix.databinding.FragmentMovieListBinding
import com.metflix.domain.entity.Movie
import com.metflix.presentation.MovieListViewModel
import com.metflix.presentation.ViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieListFragment : BindingFragment<FragmentMovieListBinding>() {
    override fun getLayoutResId() = R.layout.fragment_movie_list

    private val viewModel: MovieListViewModel by inject()
    private var movieJob: Job? = null
    lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listSavedMovies()
        viewModel.savedMovies().observe(viewLifecycleOwner, {
            when (it.status) {
                ViewState.Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                ViewState.Status.SUCCESS -> {

                    // Prevents adapter from reloading on every save/remove of movie
                    if (!this::movieAdapter.isInitialized) {
                        binding.progressBar.visibility = View.GONE
                        initRecyclerView(it.data!!)
                        getMovies()
                    } else {
                        // Attach adapter to recyclerView if it's initialized
                        binding.recyclerView.adapter = movieAdapter
                    }
                }
                ViewState.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.retryButton.visibility = View.VISIBLE
                    Toast.makeText(context, it.error?.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.retryButton.setOnClickListener {
            movieAdapter.retry()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.app_bar_watchlist -> {
                    findNavController().navigate(R.id.action_movieListFragment_to_watchlistFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initRecyclerView(data: List<Movie>) {
        movieAdapter = MovieAdapter(viewModel, data)

        binding.recyclerView.apply {
            adapter = movieAdapter.withLoadStateHeaderAndFooter(header = MovieLoadStateAdapter { movieAdapter.retry() },
                footer = MovieLoadStateAdapter { movieAdapter.retry() })
            hasFixedSize()
        }

        movieAdapter.apply {
            addLoadStateListener { loadState ->
                binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        context,
                        "Error: ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getMovies() {
        movieJob?.cancel()
        movieJob = lifecycleScope.launch {
            viewModel.listPopularMovies().catch { e ->
                throw e
            }.collect {
                movieAdapter.submitData(it)
            }
        }
    }
}