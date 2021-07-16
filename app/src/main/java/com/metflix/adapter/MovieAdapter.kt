package com.metflix.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.metflix.R
import com.metflix.common.Constants.MOVIE_ID
import com.metflix.common.Constants.MOVIE_SAVED
import com.metflix.common.Constants.MOVIE_UNSAVED
import com.metflix.databinding.ItemMovieBinding
import com.metflix.domain.entity.Movie
import com.metflix.presentation.MovieListViewModel

class MovieAdapter(private val viewModel: MovieListViewModel, private val savedMovies: List<Movie>) :
    PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(ProductDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.binding?.let { it ->
            it.movie = movie

            // Check if movie is saved
            val isMovieSaved = savedMovies.any {
                it.id == movie.id
            }

            it.saveMovieIcon.run {
                // Mark movie as saved
                if (isMovieSaved) {
                    tag = MOVIE_SAVED
                    setImageResource(R.drawable.ic_bookmark_filled_primary)
                }

                // Save/Remove movie
                setOnClickListener { icon ->
                    tag = if (tag == MOVIE_UNSAVED) {
                        setImageResource(R.drawable.ic_bookmark_filled_primary)
                        viewModel.saveMovie(movie)
                        MOVIE_SAVED
                    } else {
                        setImageResource(R.drawable.ic_bookmark_primary)
                        viewModel.removeMovie(movie)
                        MOVIE_UNSAVED
                    }
                }
            }


            it.movieCard.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putInt(MOVIE_ID, movie.id)
                bundle.putBoolean(MOVIE_SAVED, true)
                view.findNavController()
                    .navigate(R.id.action_movieListFragment_to_movieFragment, bundle)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemMovieBinding>(view)
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}