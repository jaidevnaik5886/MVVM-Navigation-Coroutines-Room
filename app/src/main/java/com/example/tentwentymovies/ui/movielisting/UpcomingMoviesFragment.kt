package com.example.tentwentymovies.ui.movielisting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tentwentymovies.R
import com.example.tentwentymovies.adapter.MoviesAdapter
import com.example.tentwentymovies.model.Movies
import com.example.tentwentymovies.utils.AppConstants
import com.example.tentwentymovies.utils.Utilities
import kotlinx.android.synthetic.main.fragment_upcoming_movies.*

class UpcomingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies),
    MoviesAdapter.MovieListener {

    lateinit var viewModel: MovieListingViewModel
    lateinit var moviesAdapter: MoviesAdapter

    val TAG = "UpcomingMoviesFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MovieListingActivity).viewModel
        setupRecyclerView()

        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(AppConstants.MOVIE_ID, it.id)
            }
            findNavController().navigate(
                R.id.action_upcomingMoviesFragment_to_movieDetailFragment, bundle
            )
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.differ.submitList(it)
        })

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer { allTables ->
            val allMovies = mutableListOf<Movies>()
            for (table in allTables) {
                allMovies.add(
                    Movies(
                        table.id,
                        table.title,
                        table.image,
                        table.release_date,
                        table.adult,
                        table.video
                    )
                )
            }
            moviesAdapter.differ.submitList(allMovies)
        })


    }

    override fun onBookClicked(title: String) {
        Toast.makeText(
            getActivity(), "$title Booked",
            Toast.LENGTH_SHORT
        ).show();
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(this)
        rv_upcoming_movies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}




