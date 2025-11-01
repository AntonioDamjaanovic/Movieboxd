package com.example.movieboxxd.auth.domain.models

data class RequestTokenBody(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)