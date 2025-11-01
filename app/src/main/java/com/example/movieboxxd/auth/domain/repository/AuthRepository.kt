package com.example.movieboxxd.auth.domain.repository

import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(username: String, password: String): Flow<Response<String>>
    fun logoutUser(): Flow<Response<Boolean>>
}