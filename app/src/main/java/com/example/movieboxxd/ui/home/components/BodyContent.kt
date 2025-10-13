package com.example.movieboxxd.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    discoverMovies: List<Movie>,
    trendingMovies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onMoreMoviesClick: (String) -> Unit
) {
    Column(modifier = modifier.background(color = BackgroundColor)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.default),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular this week",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = { onMoreMoviesClick("trending") }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "More trending movies",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
        }
        LazyRow(
            modifier = Modifier.padding(horizontal = Padding.default)
        ) {
            items(trendingMovies) {
                MovieCoverImage(
                    movie = it,
                    onMovieClick = onMovieClick
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.default),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Discover movies",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = { onMoreMoviesClick("discover") }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "More discover movies",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
        }
        LazyRow(
            modifier = Modifier.padding(horizontal = Padding.default)
        ) {
            items(discoverMovies) {
                MovieCoverImage(
                    movie = it,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}