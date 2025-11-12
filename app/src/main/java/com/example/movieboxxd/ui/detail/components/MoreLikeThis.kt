package com.example.movieboxxd.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun MoreLikeThis(
    modifier: Modifier = Modifier,
    similarMovies: List<Movie>,
    fetchMovies: () -> Unit,
    isMovieLoading: Boolean,
    onMovieClick: (Int) -> Unit
) {
    LaunchedEffect(key1 = true) {
        fetchMovies()
    }

    Column(modifier) {
        Text(
            text = "More like this",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(Padding.itemSpacing))
        LazyRow {
            item {
                AnimatedVisibility(visible = isMovieLoading) {
                    CircularProgressIndicator()
                }
            }
            items(similarMovies) {
                MovieCoverImage(movie = it, onMovieClick = onMovieClick)
            }
        }
    }
}