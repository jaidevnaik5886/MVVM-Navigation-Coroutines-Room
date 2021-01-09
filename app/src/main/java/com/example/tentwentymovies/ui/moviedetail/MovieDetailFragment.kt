package com.example.tentwentymovies.ui.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tentwentymovies.R
import com.example.tentwentymovies.VideoActivty
import com.example.tentwentymovies.ui.movielisting.MovieListingActivity
import com.example.tentwentymovies.ui.movielisting.MovieListingViewModel
import com.example.tentwentymovies.utils.AppConstants
import com.example.tentwentymovies.utils.HttpConstants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    lateinit var viewModel: MovieListingViewModel
    val args: MovieDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MovieListingActivity).viewModel
        val movieId = args.movieId
        viewModel.movieDetail(movieId)

        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { movieDetail ->
            Glide.with(this).load(HttpConstants.IMAGE_BASE_PATH + movieDetail.image)
                .into(iv_movie_image)
            txt_movie_title.text = movieDetail.title
            txt_genre.text = movieDetail.genres
            txt_overview_content.text = movieDetail.overview
            txt_date.text = movieDetail.date
            txt_rating_movie.text = getString(R.string.popularity, movieDetail.rating)

            btn_watch.setOnClickListener {
                Snackbar.make(view, "Watch Trailer", Snackbar.LENGTH_SHORT).show()
                viewModel.getMovieVideoDetail(movieId)
            }

        })

        viewModel.video.observe(viewLifecycleOwner, Observer {
            key ->
            val goToVideo = Intent(activity, VideoActivty::class.java)
            goToVideo.putExtra(AppConstants.MOVIE_PLAYBACKID, key)
            startActivity(goToVideo)
        })
    }
}