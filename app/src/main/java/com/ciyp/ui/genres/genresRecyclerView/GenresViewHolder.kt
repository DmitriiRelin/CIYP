package com.ciyp.ui.genres.genresRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.databinding.GenresItemBinding
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.Genre
import com.ciyp.domain.entites.Movie
import com.ciyp.utils.downLoader.downLoadWithByte
import com.google.firebase.storage.StorageReference

class GenresViewHolder(
    itemView: View,
    var storageReference: StorageReference,
) : RecyclerView.ViewHolder(itemView) {

    private val binding: GenresItemBinding = GenresItemBinding.bind(itemView)

    fun onBind(genre: Genre) {
        binding.imageViewPoster.setImageResource(R.color.primaryLightColor)
        when (genre.name) {
            "Action" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Adventure" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Animation" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Comedy" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Crime" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Documentary" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Drama" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Family" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Fantasy" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "History" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Horror" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Music" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Mystery" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Romance" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Thriller" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "War" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Science Fiction" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "TV Movie" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
            "Western" -> downLoadWithByte(storageReference,
                binding.imageViewPoster,
                "${genre.name?.lowercase()}.jfif")
        }
        binding.textViewTitle.text = genre.name
    }
}
