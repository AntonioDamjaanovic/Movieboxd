package com.example.movieboxxd.profile.domain.repository

import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun fetchAccountDetails(sessionId: String): Flow<Response<Profile>>
    fun fetchFavoriteMovies(accountId: Int, sessionId: String): Flow<Response<List<Movie>>>
    fun fetchRatedMovies(accountId: Int, sessionId: String): Flow<Response<List<Movie>>>
    fun fetchWatchlistMovies(accountId: Int, sessionId: String): Flow<Response<List<Movie>>>
}