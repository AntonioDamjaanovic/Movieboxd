package com.example.movieboxxd.di

import android.content.Context
import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.auth.data.mapper_impl.SessionMapper
import com.example.movieboxxd.auth.data.mapper_impl.TokenMapper
import com.example.movieboxxd.auth.data.remote.api.AuthApiService
import com.example.movieboxxd.auth.data.remote.models.RequestTokenDto
import com.example.movieboxxd.auth.data.remote.models.SessionDto
import com.example.movieboxxd.auth.data.repository_impl.AuthRepositoryImpl
import com.example.movieboxxd.auth.domain.models.RequestTokenBody
import com.example.movieboxxd.auth.domain.models.SessionBody
import com.example.movieboxxd.auth.domain.repository.AuthRepository
import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.api.ProfileApiService
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.utils.DB
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        profileApiService: ProfileApiService,
        tokenMapper: ApiMapper<RequestTokenBody, RequestTokenDto>,
        sessionMapper: ApiMapper<SessionBody, SessionDto>,
        profileMapper: ApiMapper<Profile, ProfileDto>,
        sessionManager: SessionManager
        ): AuthRepository = AuthRepositoryImpl(
        authApiService = authApiService,
        profileApiService = profileApiService,
        tokenMapper = tokenMapper,
        sessionMapper = sessionMapper,
        profileMapper = profileMapper,
        sessionManager = sessionManager
    )

    @Provides
    @Singleton
    fun provideTokenMapper(): ApiMapper<RequestTokenBody, RequestTokenDto> = TokenMapper()

    @Provides
    @Singleton
    fun provideSessionMapper(): ApiMapper<SessionBody, SessionDto> = SessionMapper()

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(): AuthApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(DB.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(AuthApiService::class.java)
    }
}