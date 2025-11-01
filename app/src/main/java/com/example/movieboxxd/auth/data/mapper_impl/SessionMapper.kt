package com.example.movieboxxd.auth.data.mapper_impl

import com.example.movieboxxd.auth.data.remote.models.SessionDto
import com.example.movieboxxd.auth.domain.models.SessionBody
import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.utils.formatEmptyValue

class SessionMapper: ApiMapper<SessionBody, SessionDto> {
    override fun mapToDomain(apiDto: SessionDto): SessionBody {
        return SessionBody(
            sessionId = formatEmptyValue(apiDto.sessionId),
            success = apiDto.success ?: false
        )
    }
}