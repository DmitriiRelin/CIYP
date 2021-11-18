package com.ciyp.datalayer.remote.dto.video

data class ResponseVideo(
    val id: Int,
    val results: List<VideoDetail>
)