package com.metflix.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.metflix.R
import com.metflix.common.BindingFragment
import com.metflix.databinding.FragmentMovieBinding
import com.metflix.utils.ResultData
import com.metflix.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject

class MovieFragment : BindingFragment<FragmentMovieBinding>(){
    override fun getLayoutResId() = R.layout.fragment_movie

    private val viewModel: MovieViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val movieId = arguments?.getInt(MOVIE_ID)
        movieId?.let {
            viewModel.getMovieDetails(it).observe(viewLifecycleOwner, {
                 if(it is ResultData.Success) {
                     binding.movie = it.data
                 } else {

                 }
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