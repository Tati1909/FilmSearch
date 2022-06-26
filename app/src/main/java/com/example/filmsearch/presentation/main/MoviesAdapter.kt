package com.example.filmsearch.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearch.R
import com.example.filmsearch.databinding.ItemMovieBinding
import com.example.filmsearch.model.Movie
import com.example.filmsearch.presentation.loadPicture

class MoviesAdapter(
    private var onItemClicked: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var moviesData: List<Movie> = listOf()

    fun setList(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    //создание вьюшки(элемента списка)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false) as
                View
        )
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)
        fun bind(movie: Movie) {
            binding.itemTitle.text = movie.fullTitle.toString()
            binding.itemRating.progress = movie.imDbRatingCount?.toInt() ?: 0
            movie.image?.let { binding.itemImage.loadPicture(it) }
            binding.root.setOnClickListener { onItemClicked(movie) }
        }
    }
}