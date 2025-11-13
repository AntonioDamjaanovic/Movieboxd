package com.example.movieboxxd.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.Padding

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