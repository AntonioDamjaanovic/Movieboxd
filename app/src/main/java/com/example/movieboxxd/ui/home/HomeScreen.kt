package com.example.movieboxxd.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.home.components.BodyContent
import com.example.movieboxxd.ui.home.components.TopContent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        AnimatedVisibility(visible = state.error != null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 2
                )
            }
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.15f)
                )
                BodyContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.85f),
                    discoverMovies = state.discoverMovies,
                    trendingMovies = state.trendingMovies,
                    onMovieClick = onMovieClick
                )
            }
        }
        LoadingView(isLoading = state.isLoading)
    }
}