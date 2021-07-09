package com.metflix.data_remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metflix.data.model.ActorsResponse
import com.metflix.data.model.MovieResponse
import com.metflix.data.source.RemoteDataSource
import com.metflix.data_remote.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(private val service: AppService): RemoteDataSource {
    override fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(25, enablePlaceholders = true),
            pagingSourceFactory = { MoviePagingSource(service) },
            initialKey = 1
        ).flow
    }

    override fun getMovieDetails(movieId: Int): LiveData<MovieResponse> = liveData {
        try {
            val response = service.getMovieDetails(movieId)
            if(response.isSuccessful) {
                response.body()?.let { emit(it) }
            }
        } catch (e: Exception) {
        }
    }

    override fun getActors(movieId: Int): LiveData<List<ActorsResponse.Actor>> = liveData {
        try {
            val response = service.getActors(movieId)
            if(response.isSuccessful) {
                response.body()?.cast?.let { emit(it) }
            }
        } catch (e: Exception) {
        }
    }
}