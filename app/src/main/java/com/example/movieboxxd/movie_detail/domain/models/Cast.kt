package com.example.movieboxxd.movie_detail.domain.models

data class Cast(
    val id: Int,
    val name: String,
    val gender: String,
    val character: String,
    val profilePath: String?
) {
    private val nameParts = name.split(" ")
    val firstName = nameParts.firstOrNull() ?: ""
    val lastName = if (nameParts.size > 1) nameParts.last() else ""

    private val characterNameParts = character.split(" ")
    val characterFirstName = characterNameParts.firstOrNull() ?: ""
    val characterLastName = if (characterNameParts.size > 1) characterNameParts.last() else ""
}
