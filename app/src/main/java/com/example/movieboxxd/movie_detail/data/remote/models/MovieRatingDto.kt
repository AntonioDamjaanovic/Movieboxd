package com.example.movieboxxd.movie_detail.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieRatingDto(
    @SerialName("value")
    val value: Double? = null
)