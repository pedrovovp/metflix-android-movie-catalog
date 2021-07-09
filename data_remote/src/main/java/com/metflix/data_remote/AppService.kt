package com.metflix.data_remote

import com.metflix.data.model.ActorsResponse
import com.metflix.data.model.MovieResponse
import com.metflix.data.model.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {
    companion object {
        const val SERVER_HOST = "https://api.themoviedb.org/3/"
        const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
    }

    @GET("movie/popular?api_key=a00a9dc308fe9d454be76235f4a75449&language=en-US")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<ListResponse<MovieResponse>>

    @GET("movie/{movie_id}?api_key=a00a9dc308fe9d454be76235f4a75449&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieResponse>

    @GET("movie/{movie_id}/credits?api_key=a00a9dc308fe9d454be76235f4a75449&language=en-US")
    suspend fun getActors(@Path("movie_id") movieId: Int): Response<ActorsResponse>
}