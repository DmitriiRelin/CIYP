package com.ciyp.ui.favorites.favoritesRecyclerVIew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ciyp.R
import com.ciyp.domain.entites.MovieDetails


class FavoriteAdapter(
    private val onItemClickListenerFavorites: (MovieDetails) -> Unit,
    private val onItemClickDelete: (MovieDetails) -> Unit,
) : ListAdapter<MovieDetails, FavoriteHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.favorites_item, parent, false),
            onItemClickListenerFavorites,
            onItemClickDelete)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bindData(getItem(position))
    }


}