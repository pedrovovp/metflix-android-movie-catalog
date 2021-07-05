package com.metflix.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.metflix.R
import com.metflix.common.BindingFragment
import com.metflix.data.paging.MovieLoadStateAdapter
import com.metflix.databinding.FragmentMovieListBinding
import com.metflix.viewmodel.MovieListViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieListFragment : BindingFragment<FragmentMovieListBinding>() {
    override fun getLayoutResId() = R.layout.fragment_movie_list

    private val viewModel: MovieListViewModel by inject()
    private var movieJob: Job? = null
    private val movieAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = movieAdapter.withLoadStateHeaderAndFooter(header = MovieLoadStateAdapter { movieAdapter.retry() },
                footer = MovieLoadStateAdapter { movieAdapter.retry() })
            hasFixedSize()
        }

        initAdapter()
        getMovies()
        binding.retryButton.setOnClickListener {
            movieAdapter.retry()
        }
    }

    private fun initAdapter() {
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
            viewModel.getPopularMovies().collect {
                movieAdapter.submitData(it)
            }
        }
    }

}