package com.example.movieboxxd.auth.data.repository_impl

import coil3.network.HttpException
import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.auth.data.remote.api.AuthApiService
import com.example.movieboxxd.auth.data.remote.models.RequestTokenDto
import com.example.movieboxxd.auth.data.remote.models.SessionDto
import com.example.movieboxxd.auth.data.remote.models.LoginBodyDto
import com.example.movieboxxd.auth.domain.models.RequestTokenBody
import com.example.movieboxxd.auth.domain.models.SessionBody
import com.example.movieboxxd.auth.domain.repository.AuthRepository
import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.profile.data.remote.api.ProfileApiService
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val profileApiService: ProfileApiService,
    private val tokenMapper: ApiMapper<RequestTokenBody, RequestTokenDto>,
    private val profileMapper: ApiMapper<Profile, ProfileDto>,
    private val sessionMapper: ApiMapper<SessionBody, SessionDto>,
    private val sessionManager: SessionManager
): AuthRepository {
    override fun loginUser(username: String, password: String): Flow<Response<String>> = flow {
        emit(Response.Loading())

        try {
            val requestTokenBody = getRequestToken()
            val loginRequestDto = validateRequestToken(username, password, requestTokenBody)
            val sessionBody = createSession(loginRequestDto)

            if (sessionBody.success) {
                sessionManager.saveSession(sessionBody.sessionId)
                val profileDto = profileApiService.fetchAccountDetails(sessionId = sessionBody.sessionId)
                profileMapper.mapToDomain(profileDto).apply {
                    sessionManager.saveAccountId(this.id)
                }
                emit(Response.Success(sessionBody.sessionId))
            } else {
                emit(Response.Error(Exception("Failed to create session")))
            }
        } catch (e: HttpException) {
            emit(Response.Error(Exception("Authentication failed: ${e.message}")))
        } catch (e: IOException) {
            emit(Response.Error(Exception("Network error. Please check your connection.")))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Response.Error(e))
        }
    }

    private suspend fun getRequestToken(): RequestTokenBody {
        val requestTokenDto = authApiService.getRequestToken()
        return tokenMapper.mapToDomain(requestTokenDto)
    }

    private suspend fun validateRequestToken(username: String, password: String, requestTokenBody: RequestTokenBody): RequestTokenDto {
        val loginBody = LoginBodyDto(username, password, requestTokenBody.requestToken)
        val loginRequestTokenDto = authApiService.validateWithLogin(loginBody = loginBody)
        return loginRequestTokenDto
    }

    private suspend fun createSession(loginRequestDto: RequestTokenDto): SessionBody {
        val sessionDto = authApiService.createSession(requestTokenDto = loginRequestDto)
        return sessionMapper.mapToDomain(sessionDto)
    }

    override fun logoutUser(): Flow<Response<Boolean>> = flow {
        emit(Response.Loading())
        try {
            sessionManager.clearSession()
            emit(Response.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Response.Error(e))
        }
    }
}