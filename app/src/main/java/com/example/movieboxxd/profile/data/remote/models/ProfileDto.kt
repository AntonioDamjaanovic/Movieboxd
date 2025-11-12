package com.example.movieboxxd.profile.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("avatar")
    val avatar: AvatarDto? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("include_adult")
    val includeAdult: Boolean? = null,
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("username")
    val username: String? = null
)