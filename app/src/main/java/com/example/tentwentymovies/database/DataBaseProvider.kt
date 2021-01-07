package com.example.tentwentymovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tentwentymovies.database.dao.MoviesDao
import com.example.tentwentymovies.database.tables.MoviesTable


@Database(
    entities = [MoviesTable::class],
    version = 1
)
abstract class DataBaseProvider : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        @Volatile
        private var instance: DataBaseProvider? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DataBaseProvider::class.java,
                "movies_db.db"
            ).build()
    }
}