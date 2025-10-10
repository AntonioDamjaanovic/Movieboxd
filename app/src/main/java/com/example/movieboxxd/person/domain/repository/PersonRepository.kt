package com.example.movieboxxd.person.domain.repository

import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun fetchPerson(personId: Int): Flow<Response<Person>>
}