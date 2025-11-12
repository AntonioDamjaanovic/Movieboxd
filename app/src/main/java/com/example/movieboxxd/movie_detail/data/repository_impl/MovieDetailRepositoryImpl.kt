package com.example.movieboxxd.movie_detail.data.repository_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.data.remote.api.MovieDetailApiService
import com.example.movieboxxd.movie_detail.data.remote.models.FavoriteMediaDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieRatingDto
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.movie_detail.data.remote.models.WatchlistMediaDto
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.repository.MovieDetailRepository
import com.example.movieboxxd.profile.domain.models.Status
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(
    private val movieDetailApiService: MovieDetailApiService,
    private val movieDetailMapper: ApiMapper<MovieDetail, MovieDetailDto>,
    private val movieMapper: ApiMapper<List<Movie>, MovieDto>,
    private val statusMapper: ApiMapper<Status, StatusDto>
): MovieDetailRepository {
    override fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> = flow {
        emit(Response.Loading())
        val movieDetailDto = movieDetailApiService.fetchMovieDetail(movieId)
        movieDetailMapper.mapToDomain(movieDetailDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchRecommendedMovies(movieId: Int): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieDetailApiService.fetchSimilarMovies(movieId)
        movieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun rateMovie(
        sessionId: String,
        movieId: Int,
        movieRatingDto: MovieRatingDto
    ): Flow<Response<Status>> = flow {
        emit(Response.Loading())
        val statusDto = movieDetailApiService.rateMovie(movieId = movieId, sessionId = sessionId, movieRatingDto = movieRatingDto)
        statusMapper.mapToDomain(statusDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun addFavoriteMovie(
        accountId: Int,
        sessionId: String,
        mediaDto: FavoriteMediaDto
    ): Flow<Response<Status>> = flow {
        emit(Response.Loading())
        val statusDto = movieDetailApiService.addFavoriteMovie(accountId = accountId, sessionId = sessionId, mediaDto = mediaDto)
        statusMapper.mapToDomain(statusDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun addMovieToWatchlist(
        accountId: Int,
        sessionId: String,
        mediaDto: WatchlistMediaDto
    ): Flow<Response<Status>> = flow {
        emit(Response.Loading())
        val statusDto = movieDetailApiService.addMovieToWatchlist(accountId = accountId, sessionId = sessionId, mediaDto = mediaDto)
        statusMapper.mapToDomain(statusDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }
}