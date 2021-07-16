package com.metflix.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.metflix.R
import com.metflix.common.Constants.MOVIE_ID
import com.metflix.common.Constants.MOVIE_SAVED
import com.metflix.common.Constants.MOVIE_UNSAVED
import com.metflix.databinding.ItemMovieBinding
import com.metflix.domain.entity.Movie
import com.metflix.presentation.WatchlistViewModel

class WatchlistAdapter(private val viewModel: WatchlistViewModel) :
    ListAdapter<Movie, WatchlistAdapter.ViewHolder>(ProductDiffCallback) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemMovieBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = getItem(position)
        holder.binding?.let {
            it.movie = movie
            it.saveMovieIcon.run {
                tag = MOVIE_SAVED
                setImageResource(R.drawable.ic_bookmark_filled_primary)
                setOnClickListener {
                    tag = if (tag == MOVIE_SAVED) {
                        setImageResource(R.drawable.ic_bookmark_primary)
                        viewModel.removeMovie(movie)
                        MOVIE_UNSAVED
                    } else {
                        setImageResource(R.drawable.ic_bookmark_filled_primary)
                        viewModel.saveMovie(movie)
                        MOVIE_SAVED
                    }
                }
            }

            it.movieCard.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putInt(MOVIE_ID, movie.id)
                bundle.putBoolean(MOVIE_SAVED, true)
                view.findNavController()
                    .navigate(R.id.action_watchlistFragment_to_movieFragment, bundle)
            }
        }
    }

    private object ProductDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
