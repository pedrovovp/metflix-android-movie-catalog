package com.metflix.ui

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
import com.metflix.data.model.Movie
import com.metflix.databinding.ItemMovieBinding

class MovieAdapter(): PagingDataAdapter<Movie, MovieAdapter.ViewHolder>(ProductDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding?.let { it ->
            it.movie=movie
            it.saveMovieIcon.tag = "Unsaved"
            it.saveMovieIcon.setOnClickListener { icon ->
                if(it.saveMovieIcon.tag == "Unsaved") {
                    it.saveMovieIcon.setImageResource(R.drawable.ic_bookmark_filled_primary)
                    it.saveMovieIcon.tag = "Saved"
                } else
                {
                    it.saveMovieIcon.setImageResource(R.drawable.ic_bookmark)
                    it.saveMovieIcon.tag = "Unsaved"
                }
            }

            it.movieCard.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putInt(MovieFragment.MOVIE_ID, movie?.id!!)
                view.findNavController().navigate(R.id.action_movieListFragment_to_movieFragment, bundle)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemMovieBinding>(view)
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