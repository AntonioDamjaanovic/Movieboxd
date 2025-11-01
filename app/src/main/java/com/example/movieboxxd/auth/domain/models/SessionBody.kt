package com.example.movieboxxd.auth.domain.models

data class SessionBody(
    val sessionId: String,
    val success: Boolean
)