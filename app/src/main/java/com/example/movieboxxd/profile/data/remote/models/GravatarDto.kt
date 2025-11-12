package com.example.movieboxxd.profile.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GravatarDto(
    @SerialName("hash")
    val hash: String? = null
)