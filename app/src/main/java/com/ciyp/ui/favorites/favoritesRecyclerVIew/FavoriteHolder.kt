package com.ciyp.ui.favorites.favoritesRecyclerVIew

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciyp.databinding.FavoritesItemBinding
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.ui.genresDetail.genresDetailsRecyclerView.GenresDetailsHolder
import com.zerobranch.layout.SwipeLayout


class FavoriteHolder(
    itemView: View,
    private val onItemClickListenerFavorites: (MovieDetails) -> Unit,
    private val onItemClickDelete: (MovieDetails) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
        private const val RELEASE_DATE_TEXT = "Release date:"
    }


    private val binding: FavoritesItemBinding = FavoritesItemBinding.bind(itemView)

    fun bindData(movie: MovieDetails) {
        binding.titleTextView.text = movie.title
        binding.dateTextView.text = "$RELEASE_DATE_TEXT ${movie.release_date}"
        Glide
            .with(itemView.context)
            .load(URL_ADDRESS + movie.poster_path)
            .fitCenter()
            .centerCrop()
            .into(binding.imageView)

        if (binding.rightView != null) {
            binding.rightView.setOnClickListener(View.OnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickDelete(movie)
                }
            })
        }

        binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT && isContinuous) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {

                    }
                }
            }

            override fun onClose() {

            }

        })

        binding.dragItem.setOnClickListener {
            onItemClickListenerFavorites(movie)
        }
    }

}