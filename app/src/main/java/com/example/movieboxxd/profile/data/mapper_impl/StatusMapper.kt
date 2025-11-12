package com.example.movieboxxd.profile.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.profile.domain.models.Status
import com.example.movieboxxd.utils.formatEmptyValue

class StatusMapper: ApiMapper<Status, StatusDto> {
    override fun mapToDomain(apiDto: StatusDto): Status {
        return Status(
            success = apiDto.success ?: false,
            statusCode = apiDto.statusCode ?: 0,
            statusMessage = formatEmptyValue(apiDto.statusMessage)
        )
    }
}