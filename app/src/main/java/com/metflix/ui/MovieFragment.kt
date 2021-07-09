package com.metflix.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.metflix.R
import com.metflix.common.BindingFragment
import com.metflix.databinding.FragmentMovieBinding
import com.metflix.presentation.MovieViewModel
import org.koin.android.ext.android.inject


class MovieFragment : BindingFragment<FragmentMovieBinding>(){
    override fun getLayoutResId() = R.layout.fragment_movie

    private val viewModel: MovieViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val actorAdapter = ActorAdapter()
        binding.movieDetails.actorsRecyclerview.apply {
            val lm =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            layoutManager = lm
            adapter = actorAdapter
        }

        val movieId = arguments?.getInt(MOVIE_ID)
        movieId?.let {
            viewModel.getMovieDetails(it).observe(viewLifecycleOwner, {
                 binding.movie = it
                 binding.movieDetails.movie = it
            })
            viewModel.getActors(it).observe(viewLifecycleOwner, {
                actorAdapter.submitList(it)
                actorAdapter.notifyDataSetChanged()
            })
        }

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}