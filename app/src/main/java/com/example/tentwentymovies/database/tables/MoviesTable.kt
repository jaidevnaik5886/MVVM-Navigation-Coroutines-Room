package com.example.tentwentymovies.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "MoviesTable"
)
data class MoviesTable(
    @PrimaryKey
    var id: String = "",
    var title: String = "",
    var image: String = "",
    var release_date: String = "",
    var adult: Boolean = false,
    var video: Boolean = false
)