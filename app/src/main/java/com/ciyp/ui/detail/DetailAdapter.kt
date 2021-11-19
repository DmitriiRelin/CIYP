package com.ciyp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.domain.entites.Cast
import com.ciyp.domain.entites.Movie
import com.ciyp.ui.home.homeRecyclerView.nested.MovieHolder

class DetailAdapter(
    val list: List<Cast>,
) : RecyclerView.Adapter<DetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        return DetailHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_item, parent, false))
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}