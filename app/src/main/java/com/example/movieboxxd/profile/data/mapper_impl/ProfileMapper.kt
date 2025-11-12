package com.example.movieboxxd.profile.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.models.AvatarDto
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.profile.domain.models.Avatar
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.utils.formatEmptyValue

class ProfileMapper(
    private val avatarMapper: ApiMapper<Avatar, AvatarDto>
) : ApiMapper<Profile, ProfileDto> {
    override fun mapToDomain(apiDto: ProfileDto): Profile {
        return Profile(
            avatar = avatarMapper.mapToDomain(apiDto = apiDto.avatar ?: AvatarDto()),
            id = apiDto.id ?: 0,
            name = formatEmptyValue(apiDto.name),
            username = formatEmptyValue(apiDto.username)
        )
    }
}