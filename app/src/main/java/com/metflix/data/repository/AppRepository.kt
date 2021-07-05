package com.metflix.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.metflix.data.model.Movie
import com.metflix.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun getMovieDetails(movieId: Int): LiveData<ResultData<Movie?>>
}