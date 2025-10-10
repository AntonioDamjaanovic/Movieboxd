package com.example.movieboxxd.movie_detail.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.utils.K
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID = "movie_id"

interface MovieDetailApiService {
    @GET("${K.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): MovieDetailDto

    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto
}