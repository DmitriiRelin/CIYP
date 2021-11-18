package com.ciyp.ui.home.homeRecyclerView.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.domain.entites.Movie

class MovieAdapter(
    private val movieItemList: List<Movie>,
    private val onItemClickListener: (Movie) -> Unit,
) :
    RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(movieItemList[position])
        }
        holder.onBind(movieItemList[position])
    }

    override fun getItemCount(): Int {
        return movieItemList.size
    }


}