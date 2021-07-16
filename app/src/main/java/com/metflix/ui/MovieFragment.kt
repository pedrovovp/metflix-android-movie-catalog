package com.metflix.ui

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.metflix.R
import com.metflix.adapter.ActorAdapter
import com.metflix.common.BindingFragment
import com.metflix.common.Constants.MOVIE_ID
import com.metflix.common.Constants.MOVIE_SAVED
import com.metflix.databinding.FragmentMovieBinding
import com.metflix.domain.entity.Movie
import com.metflix.presentation.MovieViewModel
import org.koin.android.ext.android.inject


class MovieFragment : BindingFragment<FragmentMovieBinding>(){
    override fun getLayoutResId() = R.layout.fragment_movie

    private val viewModel: MovieViewModel by inject()
    lateinit var actorAdapter: ActorAdapter
    lateinit var movie: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        setRecyclerView()
        bindData()
        setToolbar()

//        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private fun setRecyclerView() {
        actorAdapter = ActorAdapter()
        binding.movieDetails.actorsRecyclerview.apply {
            val lm =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            layoutManager = lm
            adapter = actorAdapter
        }
    }

    private fun setToolbar() {
        binding.toolbar.run {

            setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }

            val isMovieSaved = arguments?.getBoolean(MOVIE_SAVED)!!
            val checklistItem = menu.getItem(0)
            checklistItem.isChecked = isMovieSaved

            if(isMovieSaved)
                checklistItem.icon = AppCompatResources.getDrawable(context, R.drawable.ic_bookmark_filled_primary)

            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.watchList -> {
                        checklistItem.run {
                            if(isChecked) {
                                isChecked = false
                                viewModel.removeMovie(movie)
                                icon = AppCompatResources.getDrawable(context, R.drawable.ic_bookmark_primary)
                            } else {
                                isChecked = true
                                viewModel.saveMovie(movie)
                                icon = AppCompatResources.getDrawable(context, R.drawable.ic_bookmark_filled_primary)
                            }
                        }
                    }
                }
                true
            }
        }
    }

    private fun bindData() {
        val movieId = arguments?.getInt(MOVIE_ID)
        movieId?.let {
            viewModel.getMovieDetails(it).observe(viewLifecycleOwner, {
                binding.movie = it
                movie = it
                binding.movieDetails.movie = it
            })
            viewModel.getActors(it).observe(viewLifecycleOwner, {
                actorAdapter.submitList(it)
                actorAdapter.notifyDataSetChanged()
            })
        }
    }
}