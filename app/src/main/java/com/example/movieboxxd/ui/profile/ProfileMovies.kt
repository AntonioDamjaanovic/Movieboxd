package com.example.movieboxxd.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.ErrorView
import com.example.movieboxxd.ui.components.Header
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.home.components.MovieGrid
import com.example.movieboxxd.ui.theme.BackgroundColor

@Composable
fun ProfileMovies(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    type: String,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val state by profileViewModel.profileState.collectAsStateWithLifecycle()
    val movies = when (type) {
        "watched" -> state.ratedMovies
        "favorites" -> state.favoriteMovies
        "watchlist" -> state.watchlistMovies
        else -> emptyList()
    }

    Box(modifier = modifier.fillMaxWidth()) {
        when {
            state.isLoading -> {
                LoadingView()
            }
            state.error != null -> {
                ErrorView(errorMessage = state.error ?: "Unknown error")
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    Header(
                        title = when (type) {
                            "watched" -> "Watched movies"
                            "favorites" -> "Favorite movies"
                            "watchlist" -> "Watchlist"
                            else -> "Movies"
                        },
                        onBackClick = onBackClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.12f)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BackgroundColor)
                            .weight(0.85f)
                    ) {
                        MovieGrid(
                            movies = movies,
                            onMovieClick = onMovieClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}