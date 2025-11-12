package com.example.movieboxxd.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun ProfileBodyContent(
    modifier: Modifier = Modifier,
    profile: Profile,
    favoriteMovies: List<Movie>,
    ratedMovies: List<Movie>,
    watchlistMovies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProfilePicture(
                profile = profile
            )
            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.default, vertical = Padding.verticalSpacing)
            ) {
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                if (favoriteMovies.isEmpty()) {
                    Text(
                        text = "No favorite movies",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow() {
                        items(favoriteMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick
                            )
                        }
                    }
                }
            }
            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.default, vertical = Padding.verticalSpacing)
            ) {
                Text(
                    text = "Recent activity",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                if (ratedMovies.isEmpty()) {
                    Text(
                        text = "No recent activity",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow() {
                        items(ratedMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick
                            )
                        }
                    }
                }
            }
            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.default, vertical = Padding.verticalSpacing)
            ) {
                Text(
                    text = "Watchlist",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                if (watchlistMovies.isEmpty()) {
                    Text(
                        text = "Watchlist is empty",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow() {
                        items(watchlistMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick
                            )
                        }
                    }
                }
            }
        }
    }
}