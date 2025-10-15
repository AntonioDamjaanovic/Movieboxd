package com.example.movieboxxd.movie.domain.repository

import com.example.movieboxxd.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import com.example.movieboxxd.utils.Response

interface MovieRepository {
    fun fetchDiscoverMovies(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovies(): Flow<Response<List<Movie>>>
    fun searchMovie(query: String): Flow<Response<List<Movie>>>
}