package com.example.movieboxxd.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.components.Header
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun MoreMoviesScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    type: String,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val movies = when (type) {
        "trending" -> state.trendingMovies
        "discover" -> state.discoverMovies
        else -> emptyList()
    }

    Box(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = state.error != null,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Padding.default)
            ) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
        AnimatedVisibility(
            visible = !state.isLoading && state.error == null,
        ) {
            Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
            ) {
                Header(
                    title = if (type == "trending") "Popular this week" else "Discover movies",
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
        LoadingView(isLoading = state.isLoading)
    }
}

@Composable
fun MovieGrid(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxWidth()
            .padding(Padding.default)
    ) {
        items(movies) { movie ->
            MovieCoverImage(
                movie = movie,
                onMovieClick = onMovieClick,
                width = 110.dp,
                height = 150.dp
            )
        }
    }
}