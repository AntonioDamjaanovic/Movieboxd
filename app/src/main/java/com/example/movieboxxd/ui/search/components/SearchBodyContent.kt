package com.example.movieboxxd.ui.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.DefaultColor

@Composable
fun SearchBodyContent(
    movies: List<Movie>,
    isLoading: Boolean,
    onMovieClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor)
    ) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(movies) { movie ->
                    MovieListItem(
                        movie = movie,
                        onMovieClick = onMovieClick,
                    )
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DefaultColor)
                    )
                }
            }
        }
    }
}