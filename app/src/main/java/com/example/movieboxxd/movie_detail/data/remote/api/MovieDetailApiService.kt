package com.example.movieboxxd.movie_detail.data.remote.api

import com.example.movieboxxd.BuildConfig
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.movie_detail.data.remote.models.FavoriteMediaDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieRatingDto
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.movie_detail.data.remote.models.WatchlistMediaDto
import com.example.movieboxxd.utils.DB
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID = "movie_id"
private const val ACCOUNT_ID = "account_id"

interface MovieDetailApiService {
    @GET("${DB.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): MovieDetailDto

    @GET("${DB.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}/${DB.RECOMMENDED_MOVIES_ENDPOINT}")
    suspend fun fetchSimilarMovies(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieDto

    @POST("${DB.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}/${DB.ADD_RATING_ENDPOINT}")
    suspend fun rateMovie(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Body movieRatingDto: MovieRatingDto
    ): StatusDto

    @POST("${DB.ACCOUNT_ENDPOINT}/{$ACCOUNT_ID}/${DB.ADD_FAVORITE_ENDPOINT}")
    suspend fun addFavoriteMovie(
        @Path(ACCOUNT_ID) accountId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Body mediaDto: FavoriteMediaDto
    ): StatusDto

    @POST("${DB.ACCOUNT_ENDPOINT}/{$ACCOUNT_ID}/${DB.ADD_TO_WATCHLIST_ENDPOINT}")
    suspend fun addMovieToWatchlist(
        @Path(ACCOUNT_ID) accountId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("session_id") sessionId: String,
        @Body mediaDto: WatchlistMediaDto
    ): StatusDto
}