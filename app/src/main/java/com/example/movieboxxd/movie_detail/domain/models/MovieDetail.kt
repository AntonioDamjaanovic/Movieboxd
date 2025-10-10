package com.example.movieboxxd.movie_detail.domain.models


data class MovieDetail(
    val backdropPath: String,
    val genreIds: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val video: Boolean,
    val cast: List<Cast>,
    val crew: List<Crew>,
    val language: List<String>,
    val productionCountry: List<String>,
    val reviews: List<Review>,
    val runTime: String,
)
