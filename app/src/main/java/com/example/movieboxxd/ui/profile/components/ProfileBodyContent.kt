package com.example.movieboxxd.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.sp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.ui.theme.SelectedIconColor

@Composable
fun ProfileBodyContent(
    modifier: Modifier = Modifier,
    profile: Profile,
    favoriteMovies: List<Movie>,
    ratedMovies: List<Movie>,
    watchlistMovies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onMoreMoviesClick: (String) -> Unit,
    onLogoutClick: () -> Unit
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
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.biggerDefault)
            ) {
                ProfilePicture(
                    profile = profile
                )
            }
            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.default, vertical = Padding.verticalSpacing)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Favorites",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    IconButton(
                        onClick = { onMoreMoviesClick("favorites") }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "More favorite movies",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }
                }
                if (favoriteMovies.isEmpty()) {
                    Text(
                        text = "No favorite movies",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow {
                        items(favoriteMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick,
                                height = 150.dp,
                                width = 100.dp
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Recent activity",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    IconButton(
                        onClick = { onMoreMoviesClick("watched") }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "More watched movies",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }
                }
                if (ratedMovies.isEmpty()) {
                    Text(
                        text = "No recent activity",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow {
                        items(ratedMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick,
                                height = 150.dp,
                                width = 100.dp
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Watchlist",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    IconButton(
                        onClick = { onMoreMoviesClick("watchlist") }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "More Watchlist",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }
                }
                if (watchlistMovies.isEmpty()) {
                    Text(
                        text = "Watchlist is empty",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                } else {
                    LazyRow {
                        items(watchlistMovies) { movie ->
                            MovieCoverImage(
                                movie = movie,
                                onMovieClick = onMovieClick,
                                height = 150.dp,
                                width = 100.dp
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Padding.biggerItemSpacing)
                        .clickable { onMoreMoviesClick("watched") }
                ) {
                    Text(
                        text = "Films",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${ratedMovies.count()}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Padding.biggerItemSpacing)
                        .clickable { onMoreMoviesClick("favorites") }
                ) {
                    Text(
                        text = "Favorites",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${favoriteMovies.count()}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Padding.biggerItemSpacing)
                        .clickable { onMoreMoviesClick("watchlist") }
                ) {
                    Text(
                        text = "Watchlist",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${watchlistMovies.count()}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.Gray
                    )
                }
            }
            HorizontalDivider()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Padding.biggerDefault)
            ) {
                Button(
                    onClick = onLogoutClick,
                    colors = ButtonDefaults.buttonColors(containerColor = SelectedIconColor),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(Padding.innerButtonPadding)
                ) {
                    Text(
                        text = "Log out",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 50.dp)
                    )
                }
            }
        }
    }
}