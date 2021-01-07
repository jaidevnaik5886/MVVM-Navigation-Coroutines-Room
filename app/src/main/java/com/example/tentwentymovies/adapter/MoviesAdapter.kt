package com.example.tentwentymovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tentwentymovies.R
import com.example.tentwentymovies.model.Movies

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movies_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
//            Glide.with(this).load(movie.urlToImage).into(ivArticleImage)
//            tvSource.text = movie.source.name
//            tvTitle.text = movie.title
//            tvDescription.text = movie.description
//            tvPublishedAt.text = movie.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    private var onItemClickListener: ((Movies) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movies) -> Unit) {
        onItemClickListener = listener
    }
}
