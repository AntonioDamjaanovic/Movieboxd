package com.example.movieboxxd.person.data.mapper_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.person.data.remote.models.PersonDto
import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.utils.formatEmptyValue

class PersonMapper: ApiMapper<Person, PersonDto> {
    override fun mapToDomain(apiDto: PersonDto): Person {
        return Person(
            id = apiDto.id ?: 0,
            name = formatEmptyValue(apiDto.name, "name"),
            biography = formatEmptyValue(apiDto.biography, "biography"),
            profilePath = formatEmptyValue(apiDto.profilePath)
        )
    }
}