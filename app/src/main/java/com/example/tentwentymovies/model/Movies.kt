package com.example.tentwentymovies.model

data class Movies(var id: String = "",
                  var movieName: String = "",
                  var icon: String = "",
                  var rating: String = "",
                  var address: String = "",
                  var status: Boolean = false
)