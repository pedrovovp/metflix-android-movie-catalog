package com.metflix.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.metflix.data.model.ActorsData
import com.metflix.data.model.MovieData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun listPopularMovies(): Flow<PagingData<MovieData>>

    fun getMovieDetails(movieId: Int): LiveData<MovieData>

    fun getActors(movieId: Int): LiveData<List<ActorsData.Actor>>
}