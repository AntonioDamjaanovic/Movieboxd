package com.example.movieboxxd.movie_detail.domain.models

data class Review(
    val author: String,
    val content: String,
    val id: String,
    val createdAt: String,
    val rating: Double
)
