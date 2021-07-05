package com.metflix.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.metflix.data.api.AppService
import com.metflix.data.paging.MoviePagingSource
import com.metflix.data.domain.Movie
import com.metflix.utils.ResultData
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(private val service: AppService) : AppRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(25, enablePlaceholders = true),
            pagingSourceFactory = {MoviePagingSource(service)},
            initialKey = 1
        ).flow
    }

    override fun getMovieDetails(movieId: Int): LiveData<ResultData<Movie?>> = liveData {
        try {
            val response = service.getMovieDetails(movieId)
            if(response.isSuccessful) {
                emit(ResultData.Success(response.body()))
            } else {
                emit(ResultData.Error(exception = Exception("Failed to get data from endpoint")))
            }
        } catch (e: Exception) {
            emit(ResultData.Error(exception = e))
        }
    }
}