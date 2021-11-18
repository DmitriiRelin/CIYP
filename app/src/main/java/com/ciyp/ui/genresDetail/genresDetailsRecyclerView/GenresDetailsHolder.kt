package com.ciyp.ui.genresDetail.genresDetailsRecyclerView

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciyp.databinding.GenresDetailItemBinding
import com.ciyp.domain.entites.Movie

class GenresDetailsHolder(
    view: View,
) :
    RecyclerView.ViewHolder(view) {

    private val binding: GenresDetailItemBinding = GenresDetailItemBinding.bind(itemView)

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.releaseDateTextView.text = "$RELEASE_DATE_TEXT ${movie.release_date}"
        binding.voteAverageTextView.text = "$VOTE_AVERAGE_TEXT ${movie.vote_average.toString()}"
        Glide
            .with(itemView.context)
            .load(URL_ADDRESS + movie.poster_path)
            .fitCenter()
            .centerCrop()
            .into(binding.imageViewPoster)
    }

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
        private const val RELEASE_DATE_TEXT = "Release date:"
        private const val VOTE_AVERAGE_TEXT = "Vote average:"

    }
}