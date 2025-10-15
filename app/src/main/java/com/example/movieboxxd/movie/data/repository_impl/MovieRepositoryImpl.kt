package com.example.movieboxxd.movie.data.repository_impl

import com.example.movieboxxd.common.data.ApiMapper
import com.example.movieboxxd.movie.data.remote.api.MovieApiService
import com.example.movieboxxd.movie.data.remote.models.MovieDto
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie.domain.repository.MovieRepository
import com.example.movieboxxd.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
): MovieRepository {
    override fun fetchDiscoverMovies(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())

        val pages = intArrayOf(1, 2, 3, 4, 5)
        val combinedMovies = mutableListOf<Movie>()
        for (page in pages) {
            val movieDto = movieApiService.fetchDiscoverMovies(page = page)
            val moviePage = apiMapper.mapToDomain(movieDto)
            combinedMovies.addAll(moviePage)
        }

        emit(Response.Success(combinedMovies.toList()))
    }.catch { e -> emit(Response.Error(e)) }

    override fun fetchTrendingMovies(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())

        val pages = intArrayOf(1, 2, 3, 4, 5)
        val combinedMovies = mutableListOf<Movie>()
        for (page in pages) {
            val movieDto = movieApiService.fetchTrendingMovies(page = page)
            val moviePage = apiMapper.mapToDomain(movieDto)
            combinedMovies.addAll(moviePage)
        }

        emit(Response.Success(combinedMovies.toList()))
    }.catch { e -> emit(Response.Error(e)) }

    override fun searchMovie(query: String): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())

        val movieDto = movieApiService.searchMovie(query = query)
        val movie = apiMapper.mapToDomain(movieDto)

        emit(Response.Success(movie))
    }.catch { e -> emit(Response.Error(e)) }
}