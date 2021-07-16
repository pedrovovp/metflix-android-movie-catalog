package com.metflix.data_local.dao

import androidx.room.*
import com.metflix.data_local.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: Movie)

    @Delete
    suspend fun delete(vararg movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getMovies(): Flow<List<Movie>>
}