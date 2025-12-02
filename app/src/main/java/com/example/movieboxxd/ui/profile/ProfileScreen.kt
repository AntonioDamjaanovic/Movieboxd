package com.example.movieboxxd.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.ErrorView
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.home.components.TopContent
import com.example.movieboxxd.ui.profile.components.ProfileBodyContent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    onMoreMoviesClick: (String) -> Unit,
    onLogoutClick: () -> Unit
) {
    val state by profileViewModel.profileState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        profileViewModel.fetchAccountDetailsById()
        profileViewModel.fetchFavoriteMovies()
        profileViewModel.fetchRatedMovies()
        profileViewModel.fetchWatchlistMovies()
    }

    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingView()
            }
            state.error != null -> {
                ErrorView(errorMessage = state.error ?: "Unknown error")
            }
            else -> {
                state.profile?.let { profile ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        TopContent(
                            title = profile.username,
                            modifier = Modifier.weight(0.15f)
                        )
                        ProfileBodyContent(
                            profile = profile,
                            favoriteMovies = state.favoriteMovies,
                            ratedMovies = state.ratedMovies,
                            watchlistMovies = state.watchlistMovies,
                            onMovieClick = onMovieClick,
                            onMoreMoviesClick = onMoreMoviesClick,
                            onLogoutClick = onLogoutClick,
                            modifier = Modifier
                                .weight(0.85f)
                        )
                    }
                }
            }
        }
    }
}