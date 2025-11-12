package com.example.movieboxxd.profile.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.models.AvatarDto
import com.example.movieboxxd.profile.data.remote.models.GravatarDto
import com.example.movieboxxd.profile.data.remote.models.TmdbDto
import com.example.movieboxxd.profile.domain.models.Avatar
import com.example.movieboxxd.profile.domain.models.Gravatar
import com.example.movieboxxd.profile.domain.models.Tmdb

class AvatarMapper(
    private val gravatarMapper: ApiMapper<Gravatar, GravatarDto>,
    private val tmdbMapper: ApiMapper<Tmdb, TmdbDto>
): ApiMapper<Avatar, AvatarDto> {
    override fun mapToDomain(apiDto: AvatarDto): Avatar {
        return Avatar(
            gravatar = gravatarMapper.mapToDomain(apiDto = apiDto.gravatar ?: GravatarDto()),
            tmdb = tmdbMapper.mapToDomain(apiDto = apiDto.tmdb ?: TmdbDto())
        )
    }
}