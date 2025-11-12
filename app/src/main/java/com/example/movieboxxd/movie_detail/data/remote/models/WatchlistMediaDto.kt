package com.example.movieboxxd.movie_detail.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WatchlistMediaDto(
    @SerialName("media_id")
    val mediaId: Int? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    @SerialName("watchlist")
    val watchlist: Boolean? = null
)