package com.example.tentwentymovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tentwentymovies.R
import com.example.tentwentymovies.model.Movies
import com.example.tentwentymovies.utils.HttpConstants
import kotlinx.android.synthetic.main.item_movies_list.view.*

class MoviesAdapter(val movieListener: MovieListener) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

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
            Glide.with(this).load(HttpConstants.IMAGE_BASE_PATH+ movie.image).into(iv_movie_img)
            txt_movie_name.text = movie.title
            txt_movie_date.text = movie.release_date
            if (movie.adult){
                txt_movie_certificate.text = "Adult"
            }
            else{
                txt_movie_certificate.text = "Non Adult"
            }

            btn_movie_book.setOnClickListener {
                movieListener.onBookClicked(movie.title)
            }

            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    private var onItemClickListener: ((Movies) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movies) -> Unit) {
        onItemClickListener = listener
    }

    interface MovieListener{

        fun onBookClicked(title: String)
    }
}
