package com.ciyp.ui.home.homeRecyclerView.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.databinding.CategoryWithListItemBinding
import com.ciyp.domain.entites.Movie
import com.ciyp.ui.home.homeRecyclerView.nested.MovieAdapter

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: CategoryWithListItemBinding = CategoryWithListItemBinding.bind(itemView)

    fun onBind(
        categoryWithListItem: CategoryWithListItem,
        viewPool: RecyclerView.RecycledViewPool,
        onMovieClickListener: (Movie) -> Unit,
    ) {
        binding.categoryTitle.text = categoryWithListItem.categoryTitle
        binding.descriptionTitle.text = categoryWithListItem.description
        initMovieAdapter(categoryWithListItem, viewPool, onMovieClickListener)
    }


    private fun initMovieAdapter(
        categoryWithListItem: CategoryWithListItem,
        viewPool: RecyclerView.RecycledViewPool,
        onMovieClickListener: (Movie) -> Unit,
    ) {

        val layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.initialPrefetchItemCount = categoryWithListItem.movies.size

        binding.categoryRecycler.layoutManager = layoutManager
        binding.categoryRecycler.adapter =
            MovieAdapter(categoryWithListItem.movies, onMovieClickListener)
        binding.categoryRecycler.setRecycledViewPool(viewPool)
    }
}