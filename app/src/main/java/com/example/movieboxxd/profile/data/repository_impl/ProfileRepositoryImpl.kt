package com.example.movieboxxd.profile.data.repository_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.data.remote.api.ProfileApiService
import com.example.movieboxxd.profile.data.remote.models.ProfileDto
import com.example.movieboxxd.movie_detail.data.remote.models.StatusDto
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.profile.domain.models.Status
import com.example.movieboxxd.profile.domain.repository.ProfileRepository
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl(
    private val profileApiService: ProfileApiService,
    private val profileMapper: ApiMapper<Profile, ProfileDto>,
    private val movieMapper: ApiMapper<List<Movie>, MovieDto>,
    private val statusMapper: ApiMapper<Status, StatusDto>
): ProfileRepository {

    override fun fetchAccountDetails(
        sessionId: String
    ): Flow<Response<Profile>> = flow {
        emit(Response.Loading())
        val profileDto = profileApiService.fetchAccountDetails(sessionId = sessionId)
        profileMapper.mapToDomain(profileDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchFavoriteMovies(
        accountId: Int,
        sessionId: String
    ): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = profileApiService.fetchFavoriteMovies(accountId = accountId, sessionId = sessionId)
        movieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchRatedMovies(
        accountId: Int,
        sessionId: String
    ): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = profileApiService.fetchRatedMovies(accountId = accountId, sessionId = sessionId)
        movieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchWatchlistMovies(
        accountId: Int,
        sessionId: String
    ): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = profileApiService.fetchWatchlistMovies(accountId = accountId, sessionId = sessionId)
        movieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }
}