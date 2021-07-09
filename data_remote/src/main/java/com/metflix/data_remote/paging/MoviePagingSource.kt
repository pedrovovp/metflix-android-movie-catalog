package com.metflix.data_remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.metflix.data.model.MovieResponse
import com.metflix.data_remote.AppService
import java.lang.Exception

class MoviePagingSource(private val service: AppService) : PagingSource<Int, MovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val pageIndex = params.key!!
            val response = service.getPopularMovies(pageIndex).body()!!

            LoadResult.Page(
                data = response.results,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = if (pageIndex == response.total_pages) null else pageIndex + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}