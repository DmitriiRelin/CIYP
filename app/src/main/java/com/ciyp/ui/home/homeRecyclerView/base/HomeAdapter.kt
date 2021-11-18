package com.ciyp.ui.home.homeRecyclerView.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.domain.entites.Movie

class HomeAdapter(
    private val itemList: List<CategoryWithListItem>,
    private val onMovieClickListener: (Movie) -> Unit,
) : RecyclerView.Adapter<HomeViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.category_with_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(itemList[position], viewPool, onMovieClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}