package com.example.movieboxxd.profile.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.models.GravatarDto
import com.example.movieboxxd.profile.domain.models.Gravatar
import com.example.movieboxxd.utils.formatEmptyValue

class GravatarMapper: ApiMapper<Gravatar, GravatarDto> {
    override fun mapToDomain(apiDto: GravatarDto): Gravatar {
        return Gravatar(
            hash = formatEmptyValue(apiDto.hash)
        )
    }
}