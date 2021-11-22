package com.ciyp.utils.youtubeVideo

import android.view.View
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import com.ciyp.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeLoader(
    val lifecycle: Lifecycle,
    val youTubePlayerView: YouTubePlayerView,
    val errorImageView: ImageView,
) {

    var counter = 0

    fun loadVideo(
        listUrls: List<String>,
    ) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                if (listUrls.isNotEmpty()) {
                    youTubePlayer.loadVideo(listUrls[counter], 0f)
                }
            }

            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError,
            ) {
                super.onError(youTubePlayer, error)
                counter++
                if (counter <= listUrls.lastIndex) {
                    loadVideo(listUrls)
                } else {
                    youTubePlayerView.visibility = View.GONE
                    errorImageView.setImageResource(R.drawable.ic_baseline_missed_video_call_24)
                    errorImageView.visibility = View.VISIBLE
                }

            }
        })
    }


}