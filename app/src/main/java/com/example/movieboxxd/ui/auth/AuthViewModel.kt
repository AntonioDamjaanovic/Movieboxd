package com.example.movieboxxd.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieboxxd.auth.data.local.SessionManager
import com.example.movieboxxd.auth.domain.repository.AuthRepository
import com.example.movieboxxd.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
): ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        repository.loginUser(username, password).collectAndHandle(
            onError = { error ->
                _authState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _authState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ) { sessionId ->
            _authState.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    sessionId = sessionId,
                    isLoggedIn = true
                )
            }
        }
    }

    fun logout() = viewModelScope.launch {
        repository.logoutUser().collectAndHandle(
            onError = { error ->
                _authState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _authState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ) {
            _authState.update { AuthState() }
        }
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            sessionManager.isLoggedIn().collect { isLoggedIn ->
                _authState.update { it.copy(isLoggedIn = isLoggedIn) }
            }
        }
    }
}

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val sessionId: String? = null,
    val isLoggedIn: Boolean = false
)