package com.example.movieboxxd.movie.domain.repository

import com.example.movieboxxd.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import com.example.movieboxxd.utils.Response

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}