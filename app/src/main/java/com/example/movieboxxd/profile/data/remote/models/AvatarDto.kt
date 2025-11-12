package com.example.movieboxxd.profile.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarDto(
    @SerialName("gravatar")
    val gravatar: GravatarDto? = null,
    @SerialName("tmdb")
    val tmdb: TmdbDto? = null
)