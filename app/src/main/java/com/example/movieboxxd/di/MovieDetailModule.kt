package com.example.movieboxxd.di

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.data.mapper_impl.MovieDetailMapper
import com.example.movieboxxd.movie_detail.data.remote.api.MovieDetailApiService
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.movie_detail.data.repository_impl.MovieDetailRepositoryImpl
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.repository.MovieDetailRepository
import com.example.movieboxxd.profile.domain.models.Status
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
object MovieDetailModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        movieDetailMapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>,
        statusMapper: ApiMapper<Status, StatusDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailApiService = movieDetailApiService,
        movieDetailMapper = movieDetailMapper,
        movieMapper = movieMapper,
        statusMapper = statusMapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapper()

    @Provides
    @Singleton
    fun provideMovieDetailApiService(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(DB.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieDetailApiService::class.java)
    }
}