package com.example.movieboxxd.auth.data.mapper_impl

import com.example.movieboxxd.auth.data.remote.models.RequestTokenDto
import com.example.movieboxxd.auth.domain.models.RequestTokenBody
import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.utils.formatEmptyValue

class TokenMapper: ApiMapper<RequestTokenBody, RequestTokenDto> {
    override fun mapToDomain(apiDto: RequestTokenDto): RequestTokenBody {
        return RequestTokenBody(
            expiresAt = formatEmptyValue(apiDto.expiresAt),
            requestToken = formatEmptyValue(apiDto.requestToken),
            success = apiDto.success ?: false,
        )
    }
}