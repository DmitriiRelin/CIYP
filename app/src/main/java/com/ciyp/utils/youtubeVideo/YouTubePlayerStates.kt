package com.ciyp.utils.youtubeVideo

import com.google.android.material.appbar.AppBarLayout
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlin.math.abs

fun YouTubePlayerView.pause() {
    getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
        override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
            youTubePlayer.pause()
        }
    })
}

fun YouTubePlayerView.play() {
    getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
        override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
            youTubePlayer.play()
        }
    })
}

fun AppBarLayout.addOnCloseListener(onClose: () -> Unit, onOpen: () -> Unit) {
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        appBarLayout?.let {
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0)
                onClose()
            else {
                onOpen()
            }
        }
    })
}