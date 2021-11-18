package com.ciyp.ui.genres.genresRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.Genre
import com.google.firebase.storage.StorageReference

class GenresAdapter(
    var storageReference: StorageReference,
    private val onItemClickListener: (Genre) -> Unit,
) : RecyclerView.Adapter<GenresViewHolder>() {

    var listOfGenres: List<Genre> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GenresViewHolder {
        return GenresViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.genres_item, viewGroup, false), storageReference)
    }

    override fun onBindViewHolder(viewHolder: GenresViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            onItemClickListener.invoke(listOfGenres[position])
        }
        viewHolder.onBind(listOfGenres[position])
    }

    override fun getItemCount(): Int {
        return listOfGenres.size
    }
}