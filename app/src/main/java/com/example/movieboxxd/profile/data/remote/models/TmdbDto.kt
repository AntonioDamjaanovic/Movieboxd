package com.example.movieboxxd.profile.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbDto(
    @SerialName("avatar_path")
    val avatarPath: String? = null
)