package com.example.movieboxxd.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.profile.domain.repository.ProfileRepository
import com.example.movieboxxd.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val sessionManager: SessionManager
): ViewModel() {
    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    init {
        fetchAccountDetailsById()
        fetchFavoriteMovies()
        fetchRatedMovies()
        fetchWatchlistMovies()
    }

    fun fetchAccountDetailsById() = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _profileState.update {
                it.copy(isLoading = false, error = "Profile not found")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            repository.fetchAccountDetails(sessionId = sessionId)
                .collectAndHandle(
                    onError = { error ->
                        _profileState.update {
                            it.copy(isLoading = false, error = error?.message)
                        }
                    },
                    onLoading = {
                        _profileState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }
            ) { profile ->
                _profileState.update {
                    it.copy(isLoading = false, error = null, profile = profile)
                }
            }
        }
    }

    fun fetchFavoriteMovies() = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _profileState.update {
                it.copy(isLoading = false, error = "Error with fetching favorite movies")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            repository.fetchFavoriteMovies(accountId = accountId, sessionId = sessionId).collectAndHandle(
                onError = { error ->
                    _profileState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _profileState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movies ->
                _profileState.update {
                    it.copy(isLoading = false, error = null, favoriteMovies = movies)
                }
            }
        }
    }

    fun fetchRatedMovies() = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _profileState.update {
                it.copy(isLoading = false, error = "Error with fetching rated movies")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            repository.fetchRatedMovies(accountId = accountId, sessionId = sessionId).collectAndHandle(
                onError = { error ->
                    _profileState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _profileState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movies ->
                _profileState.update {
                    it.copy(isLoading = false, error = null, ratedMovies = movies)
                }
            }
        }
    }

    fun fetchWatchlistMovies() = viewModelScope.launch {
        val accountId = sessionManager.getAccountId().first()
        if (accountId == -1) {
            _profileState.update {
                it.copy(isLoading = false, error = "Error with fetching watchlist movies")
            }
        } else {
            val sessionId = sessionManager.getSessionId().first()
            repository.fetchWatchlistMovies(accountId = accountId, sessionId = sessionId).collectAndHandle(
                onError = { error ->
                    _profileState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _profileState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movies ->
                _profileState.update {
                    it.copy(isLoading = false, error = null, watchlistMovies = movies)
                }
            }
        }
    }
}

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val favoriteMovies: List<Movie> = emptyList(),
    val ratedMovies: List<Movie> = emptyList(),
    val watchlistMovies: List<Movie> = emptyList()
)