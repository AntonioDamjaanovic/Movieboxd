package com.example.movieboxxd.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Padding.default)
            .clickable { onMovieClick(movie.id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieCoverImage(
            movie = movie,
            onMovieClick = onMovieClick,
            height = 120.dp,
            width = 80.dp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.default)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}