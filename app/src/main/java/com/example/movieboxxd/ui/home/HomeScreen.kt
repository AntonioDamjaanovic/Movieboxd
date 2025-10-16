package com.example.movieboxxd.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.ErrorView
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.home.components.BodyContent
import com.example.movieboxxd.ui.home.components.TopContent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    onMoreMoviesClick: (String) -> Unit
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingView()
            }
            state.error != null -> {
                ErrorView(errorMessage = state.error ?: "Unknown error")
            }
            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopContent(
                        title = "Movieboxd",
                        modifier = Modifier
                            .weight(0.15f)
                    )
                    BodyContent(
                        modifier = Modifier
                            .weight(0.85f),
                        discoverMovies = state.discoverMovies.take(18),
                        trendingMovies = state.trendingMovies.take(18),
                        onMovieClick = onMovieClick,
                        onMoreMoviesClick = onMoreMoviesClick
                    )
                }
            }
        }
    }
}