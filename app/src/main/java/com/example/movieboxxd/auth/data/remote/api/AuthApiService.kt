package com.example.movieboxxd.auth.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.auth.data.remote.models.RequestTokenDto
import com.example.movieboxxd.auth.data.remote.models.SessionDto
import com.example.movieboxxd.auth.data.remote.models.LoginBodyDto
import com.example.movieboxxd.auth.domain.models.RequestTokenBody
import com.example.movieboxxd.utils.DBConstants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @GET("${DBConstants.AUTHENTICATION_ENDPOINT}/${DBConstants.TOKEN_ENDPOINT}/${DBConstants.NEW_ENDPOINT}")
    suspend fun getRequestToken(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): RequestTokenDto

    @POST("${DBConstants.AUTHENTICATION_ENDPOINT}/${DBConstants.TOKEN_ENDPOINT}/${DBConstants.LOGIN_ENDPOINT}")
    suspend fun validateWithLogin(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Body loginBody: LoginBodyDto
    ): RequestTokenDto

    @POST("${DBConstants.AUTHENTICATION_ENDPOINT}/${DBConstants.SESSION_ENDPOINT}/${DBConstants.NEW_ENDPOINT}")
    suspend fun createSession(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Body requestTokenDto: RequestTokenDto
    ): SessionDto
}