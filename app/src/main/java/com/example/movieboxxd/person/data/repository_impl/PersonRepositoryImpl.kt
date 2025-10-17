package com.example.movieboxxd.person.data.repository_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.person.data.remote.api.PersonApiService
import com.example.movieboxxd.person.data.remote.models.PersonDto
import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.person.domain.repository.PersonRepository
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PersonRepositoryImpl(
    private val personApiService: PersonApiService,
    private val apiMapper: ApiMapper<Person, PersonDto>
): PersonRepository {
    override fun fetchPerson(personId: Int): Flow<Response<Person>> = flow {
        emit(Response.Loading())
        val personDto = personApiService.fetchPersonDetail(personId)

        apiMapper.mapToDomain(personDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }
}