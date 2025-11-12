package com.example.movieboxxd.di

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.mapper_impl.MovieApiMapper
import com.example.movieboxxd.movie.data.remote.api.MovieApiService
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.data.repository_impl.MovieRepositoryImpl
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie.domain.repository.MovieRepository
import com.example.movieboxxd.utils.DB
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
object MovieModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieRepository = MovieRepositoryImpl(
        movieApiService, mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<List<Movie>, MovieDto> = MovieApiMapper()

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(DB.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApiService::class.java)
    }
}