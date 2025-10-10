package com.example.movieboxxd.movie_detail.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetailsDto(
    @SerialName("avatar_path")
    val avatarPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("username")
    val username: String? = null
)