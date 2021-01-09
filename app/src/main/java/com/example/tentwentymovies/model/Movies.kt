package com.example.tentwentymovies.model

data class Movies(var id: String = "",
                  var title: String = "",
                  var image: String = "",
                  var release_date: String = "",
                  var adult: Boolean = false,
                  var video: Boolean = false

)