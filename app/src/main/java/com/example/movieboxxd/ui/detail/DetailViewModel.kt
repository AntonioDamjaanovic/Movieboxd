package com.example.movieboxxd.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.data.remote.models.FavoriteMediaDto
import com.example.movieboxxd.movie_detail.data.remote.models.MovieRatingDto
import com.example.movieboxxd.movie_detail.data.remote.models.WatchlistMediaDto
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.repository.MovieDetailRepository
import com.example.movieboxxd.utils.DB
import com.example.movieboxxd.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
    private val sessionManager: SessionManager,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    private val id: Int = savedStateHandle.get<Int>(DB.MOVIE_ID) ?: -1

    init {
        fetchMovieDetailById()
    }

    private fun fetchMovieDetailById() = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Movie not found")
            }
        } else {
            repository.fetchMovieDetail(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movieDetail ->
                    _detailState.update {
                        it.copy(isLoading = false, error = null, movieDetail = movieDetail)
                    }
            }
        }
    }

    fun fetchRecommendedMovies() = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Similar movies not found")
            }
        } else {
            repository.fetchRecommendedMovies(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isMovieLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isMovieLoading = true, error = null)
                    }
                }
            ) { movies ->
                _detailState.update {
                    it.copy(isMovieLoading = false, error = null, recommendedMovies = movies)
                }
            }
        }
    }

    fun rateMovie(movieRating: Double) = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Movie not found")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            val movieRatingDto = MovieRatingDto(
                value = movieRating
            )
            repository.rateMovie(movieId = id, sessionId = sessionId, movieRatingDto = movieRatingDto)
                .collectAndHandle(
                    onError = { error ->
                        _detailState.update {
                            it.copy(isLoading = false, error = error?.message)
                        }
                    },
                    onLoading = {
                        _detailState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }
                ) { status ->
                    if (status.success) {
                        _detailState.update { it.copy(isLoading = false, error = null) }
                    } else {
                        _detailState.update { it.copy(isLoading = false, error = "Failed to rate a movie") }
                    }
                }
        }
    }

    fun addFavoriteMovie(movieId: Int) = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Error with marking movie as a favorite")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            val mediaDto = FavoriteMediaDto(
                favorite = true,
                mediaType = "movie",
                mediaId = movieId,
            )
            repository.addFavoriteMovie(accountId = accountId, sessionId = sessionId, mediaDto = mediaDto).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { status ->
                if (status.success) {
                    _detailState.update { it.copy(isLoading = false, error = null) }
                } else {
                    _detailState.update { it.copy(isLoading = false, error = "Failed to add favorite movie") }
                }
            }
        }
    }

    fun addMovieToWatchlist(movieId: Int) = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Error with adding movie to watchlist")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            val mediaDto = WatchlistMediaDto(
                watchlist = true,
                mediaType = "movie",
                mediaId = movieId,
            )
            repository.addMovieToWatchlist(accountId = accountId, sessionId = sessionId, mediaDto = mediaDto).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { status ->
                if (status.success) {
                    _detailState.update { it.copy(isLoading = false, error = null) }
                } else {
                    _detailState.update { it.copy(isLoading = false, error = "Failed to add movie to watchlist") }
                }
            }
        }
    }
}

data class DetailState(
    val movieDetail: MovieDetail? = null,
    val recommendedMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isMovieLoading: Boolean = false
)