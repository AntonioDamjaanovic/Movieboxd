package com.example.movieboxxd.profile.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.models.TmdbDto
import com.example.movieboxxd.profile.domain.models.Tmdb
import com.example.movieboxxd.utils.formatEmptyValue

class TmdbMapper: ApiMapper<Tmdb, TmdbDto> {
    override fun mapToDomain(apiDto: TmdbDto): Tmdb {
        return Tmdb(
            avatarPath = formatEmptyValue(apiDto.avatarPath)
        )
    }
}