package com.example.movieboxxd.di

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.person.data.mapper_impl.PersonMapper
import com.example.movieboxxd.person.data.remote.api.PersonApiService
import com.example.movieboxxd.person.data.remote.models.PersonDto
import com.example.movieboxxd.person.data.repository_impl.PersonRepositoryImpl
import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.person.domain.repository.PersonRepository
import com.example.movieboxxd.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providePersonRepository(
        personApiService: PersonApiService,
        personMapper: ApiMapper<Person, PersonDto>
    ): PersonRepository = PersonRepositoryImpl(
        personApiService = personApiService,
        apiMapper = personMapper
    )

    @Provides
    @Singleton
    fun providePersonMapper(): ApiMapper<Person, PersonDto> = PersonMapper()

    @Provides
    @Singleton
    fun providePersonApiService(): PersonApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(PersonApiService::class.java)
    }
}