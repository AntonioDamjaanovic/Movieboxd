package com.example.movieboxxd.movie.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(K.DISCOVER_MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET(K.SEARCH_URL_ENDPOINT)
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieDto
}