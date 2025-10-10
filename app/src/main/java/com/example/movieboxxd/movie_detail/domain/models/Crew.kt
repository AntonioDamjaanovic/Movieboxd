package com.example.movieboxxd.movie_detail.domain.models

data class Crew(
    val id: Int,
    val name: String,
    val gender: String,
    val job: String,
    val profilePath: String?
) {
    private val nameParts = name.split(" ")
    val firstName = nameParts.firstOrNull() ?: ""
    val lastName = if (nameParts.size > 1) nameParts.last() else ""
}