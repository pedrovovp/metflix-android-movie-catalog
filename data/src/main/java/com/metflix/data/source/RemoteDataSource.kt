package com.metflix.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.metflix.data.model.ActorsResponse
import com.metflix.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getPopularMovies(): Flow<PagingData<MovieResponse>>

    fun getMovieDetails(movieId: Int): LiveData<MovieResponse>

    fun getActors(movieId: Int): LiveData<List<ActorsResponse.Actor>>
}