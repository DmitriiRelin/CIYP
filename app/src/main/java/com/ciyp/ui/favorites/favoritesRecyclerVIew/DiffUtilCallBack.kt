package com.ciyp.ui.favorites.favoritesRecyclerVIew

import androidx.recyclerview.widget.DiffUtil
import com.ciyp.domain.entites.MovieDetails

class DiffUtilCallBack : DiffUtil.ItemCallback<MovieDetails>() {

    override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem == newItem
    }

}