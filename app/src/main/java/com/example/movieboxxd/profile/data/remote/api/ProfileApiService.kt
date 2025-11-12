package com.example.movieboxxd.profile.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.utils.DB
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ACCOUNT_ID = "account_id"

interface ProfileApiService {
    @GET(DB.ACCOUNT_ENDPOINT)
    suspend fun fetchAccountDetails(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Query("include_adult") includeAdult: Boolean = false,
    ): ProfileDto

    @GET("${DB.ACCOUNT_ENDPOINT}/{$ACCOUNT_ID}/${DB.FAVORITES_ENDPOINT}")
    suspend fun fetchFavoriteMovies(
        @Path(ACCOUNT_ID) accountId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String = "created_at.desc"
    ): MovieDto

    @GET("${DB.ACCOUNT_ENDPOINT}/{$ACCOUNT_ID}/${DB.RATED_MOVIES_ENDPOINT}")
    suspend fun fetchRatedMovies(
        @Path(ACCOUNT_ID) accountId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String = "created_at.desc"
    ): MovieDto

    @GET("${DB.ACCOUNT_ENDPOINT}/{$ACCOUNT_ID}/${DB.WATCHLIST_ENDPOINT}")
    suspend fun fetchWatchlistMovies(
        @Path(ACCOUNT_ID) accountId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Query("sort_by") sortBy: String = "created_at.desc"
    ): MovieDto
}