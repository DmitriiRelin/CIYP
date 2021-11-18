package com.ciyp.ui.genresDetail.genresDetailsRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.ciyp.R
import com.ciyp.domain.entites.Movie

class GenresDetailsAdapter(private val onMovieClickListener: (Movie) -> Unit) :
    PagingDataAdapter<Movie, GenresDetailsHolder>(
        DiffUtilCallBack()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresDetailsHolder {
        return GenresDetailsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.genres_detail_item, parent, false))
    }

    override fun onBindViewHolder(holder: GenresDetailsHolder, position: Int) {
        holder.itemView.setOnClickListener {
            getItem(position)?.let { view -> onMovieClickListener.invoke(view) }
        }
        getItem(position)?.let { holder.bind(it) }
    }

}