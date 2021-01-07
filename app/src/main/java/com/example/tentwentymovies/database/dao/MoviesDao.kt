package com.example.tentwentymovies.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tentwentymovies.database.tables.MoviesTable


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movies: MoviesTable)

    @Query("SELECT * FROM MoviesTable")
    fun getAllMovies(): LiveData<MoviesTable>

}