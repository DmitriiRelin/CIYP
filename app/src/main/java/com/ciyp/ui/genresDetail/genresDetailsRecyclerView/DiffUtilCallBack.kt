package com.ciyp.ui.genresDetail.genresDetailsRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.ciyp.domain.entites.Movie

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ): Boolean {
        return oldItem == newItem
    }

}