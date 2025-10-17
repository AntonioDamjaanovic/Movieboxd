package com.example.movieboxxd.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.components.ErrorView
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
        }
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