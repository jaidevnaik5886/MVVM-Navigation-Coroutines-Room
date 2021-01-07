package com.example.tentwentymovies.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "MoviesTable"
)
data class MoviesTable(
    @PrimaryKey
    var id: String = "",
    var movieName: String = "",
    var icon: String = "",
    var rating: String = "",
    var address: String = "",
    var status: Boolean = false
)