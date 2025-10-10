package com.example.movieboxxd.person.domain.models

data class Person(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val placeOfBirth: String,
    val deathday: String,
    val gender: String,
    val profilePath: String?
)
