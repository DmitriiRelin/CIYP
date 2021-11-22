package com.ciyp.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ciyp.R
import com.ciyp.databinding.DetailItemBinding
import com.ciyp.domain.entites.Cast
import com.ciyp.utils.glide.loadImage

class DetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val URL_ADDRESS = "https://image.tmdb.org/t/p/original"
    }

    private val binding: DetailItemBinding = DetailItemBinding.bind(itemView)

    fun onBind(cast: Cast) {
        binding.nameTextView.text = cast.name
        if (cast.profile_path != null) {
            loadImage(itemView.context,
                URL_ADDRESS + cast.profile_path,
                binding.profilePathImageView)
        } else {
            loadImage(itemView.context,
                R.drawable.ic_baseline_empty_cast_avatar_24,
                binding.profilePathImageView)
        }
    }
}