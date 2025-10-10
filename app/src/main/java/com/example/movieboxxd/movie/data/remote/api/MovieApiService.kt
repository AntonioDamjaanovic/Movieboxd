package com.example.movieboxxd.movie.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto
}