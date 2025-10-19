package com.example.movieboxxd.movie_detail.domain.repository

import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchRecommendedMovies(movieId: Int): Flow<Response<List<Movie>>>
}