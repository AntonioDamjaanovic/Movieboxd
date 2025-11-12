package com.example.movieboxxd.movie_detail.domain.repository

import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.data.remote.models.FavoriteMediaDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieRatingDto
import com.example.movieboxxd.movie_detail.data.remote.models.WatchlistMediaDto
import com.example.movieboxxd.profile.domain.models.Status
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchRecommendedMovies(movieId: Int): Flow<Response<List<Movie>>>
    fun rateMovie(sessionId: String, movieId: Int, movieRatingDto: MovieRatingDto): Flow<Response<Status>>
    fun addFavoriteMovie(accountId: Int, sessionId: String, mediaDto: FavoriteMediaDto): Flow<Response<Status>>
    fun addMovieToWatchlist(accountId: Int, sessionId: String, mediaDto: WatchlistMediaDto): Flow<Response<Status>>
}