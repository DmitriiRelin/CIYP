package com.ciyp.ui.home.homeRecyclerView.nested

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciyp.R
import com.ciyp.databinding.MovieItemBinding
import com.ciyp.domain.entites.Movie

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: MovieItemBinding = MovieItemBinding.bind(itemView)

    fun onBind(movie: Movie) {
        binding.textViewTitle.text = movie.title
        binding.textViewVoteAverage.text = movie.vote_average.toString()
        Glide.with(itemView.context)
            .load(URL_ADDRESS + movie.poster_path)
            .placeholder(R.color.primaryColor)
            .fitCenter()
            .centerCrop()
            .into(binding.imageViewPoster)
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }

}