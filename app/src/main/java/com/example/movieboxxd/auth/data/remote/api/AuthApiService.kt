package com.example.movieboxxd.auth.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.auth.data.remote.models.RequestTokenDto
import com.example.movieboxxd.auth.data.remote.models.SessionDto
import com.example.movieboxxd.auth.data.remote.models.LoginBodyDto
import com.example.movieboxxd.utils.DB
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @GET("${DB.AUTHENTICATION_ENDPOINT}/${DB.TOKEN_ENDPOINT}/${DB.NEW_ENDPOINT}")
    suspend fun getRequestToken(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): RequestTokenDto

    @POST("${DB.AUTHENTICATION_ENDPOINT}/${DB.TOKEN_ENDPOINT}/${DB.LOGIN_ENDPOINT}")
    suspend fun validateWithLogin(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Body loginBody: LoginBodyDto
    ): RequestTokenDto

    @POST("${DB.AUTHENTICATION_ENDPOINT}/${DB.SESSION_ENDPOINT}/${DB.NEW_ENDPOINT}")
    suspend fun createSession(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Body requestTokenDto: RequestTokenDto
    ): SessionDto
}