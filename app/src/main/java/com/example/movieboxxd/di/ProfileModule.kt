package com.example.movieboxxd.di

import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.data.mapper_impl.AvatarMapper
import com.example.movieboxxd.profile.data.mapper_impl.GravatarMapper
import com.example.movieboxxd.profile.data.mapper_impl.ProfileMapper
import com.example.movieboxxd.profile.data.mapper_impl.StatusMapper
import com.example.movieboxxd.profile.data.mapper_impl.TmdbMapper
import com.example.movieboxxd.profile.data.remote.api.ProfileApiService
import com.example.movieboxxd.profile.data.remote.models.AvatarDto
import com.example.movieboxxd.profile.data.remote.models.GravatarDto
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.profile.data.remote.models.TmdbDto
import com.example.movieboxxd.profile.data.repository_impl.ProfileRepositoryImpl
import com.example.movieboxxd.profile.domain.models.Avatar
import com.example.movieboxxd.profile.domain.models.Gravatar
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.profile.domain.models.Status
import com.example.movieboxxd.profile.domain.models.Tmdb
import com.example.movieboxxd.profile.domain.repository.ProfileRepository
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
object ProfileModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApiService: ProfileApiService,
        profileMapper: ApiMapper<Profile, ProfileDto>,
        movieApiMapper: ApiMapper<List<Movie>, MovieDto>,
        statusMapper: ApiMapper<Status, StatusDto>
    ): ProfileRepository = ProfileRepositoryImpl(
        profileApiService = profileApiService,
        profileMapper = profileMapper,
        movieMapper = movieApiMapper,
        statusMapper = statusMapper
    )

    @Provides
    @Singleton
    fun provideProfileMapper(
        avatarMapper: ApiMapper<Avatar, AvatarDto>
    ): ApiMapper<Profile, ProfileDto> = ProfileMapper(avatarMapper)

    @Provides
    @Singleton
    fun provideAvatarMapper(
        gravatarMapper: ApiMapper<Gravatar, GravatarDto>,
        tmdbMapper: ApiMapper<Tmdb, TmdbDto>
    ): ApiMapper<Avatar, AvatarDto> = AvatarMapper(
        gravatarMapper = gravatarMapper,
        tmdbMapper = tmdbMapper
    )

    @Provides
    @Singleton
    fun provideStatusMapper(): ApiMapper<Status, StatusDto> = StatusMapper()

    @Provides
    @Singleton
    fun provideTmdbMapper(): ApiMapper<Tmdb, TmdbDto> = TmdbMapper()

    @Provides
    @Singleton
    fun provideGravatarMapper(): ApiMapper<Gravatar, GravatarDto> = GravatarMapper()

    @Provides
    @Singleton
    fun provideProfileApiService(): ProfileApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(DB.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ProfileApiService::class.java)
    }
}