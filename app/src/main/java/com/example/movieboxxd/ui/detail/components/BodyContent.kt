package com.example.movieboxxd.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.movie_detail.domain.models.Review
import com.example.movieboxxd.ui.components.MovieCoverImage
import com.example.movieboxxd.ui.home.components.MovieCard
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun DetailBodyContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    movies: List<Movie>,
    isMovieLoading: Boolean,
    fetchMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit,
) {
    Column(modifier.background(color = BackgroundColor)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.default)
                .background(color = BackgroundColor)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    movieDetail.genreIds.forEachIndexed { index, genreText ->
                        Text(
                            text = genreText,
                            modifier = Modifier.padding(6.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )

                        if (index != movieDetail.genreIds.lastIndex) {
                            Text(
                                text = " \u2022 ",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                            )
                        }
                    }
                }
                Text(
                    text = movieDetail.runTime,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(Padding.itemSpacing))
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(Padding.verticalSpacing))
            Text(
                text = movieDetail.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(Padding.verticalSpacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.itemSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cast",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Cast",
                        tint = Color.White
                    )
                }
            }

            LazyRow {
                items(movieDetail.cast) {
                    ActorItem(
                        cast = it,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onActorClick(it.id) }
                    )
                    Spacer(modifier = Modifier.width(Padding.default))
                }
            }
            Spacer(modifier = Modifier.height(Padding.verticalSpacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Padding.itemSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Crew",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Crew",
                        tint = Color.White
                    )
                }
            }

            LazyRow {
                items(movieDetail.crew) {
                    CrewItem(
                        crew = it,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onActorClick(it.id) }
                    )
                    Spacer(modifier = Modifier.width(Padding.default))
                }
            }
            Spacer(modifier = Modifier.height(Padding.verticalSpacing))

            MovieInfoItem(
                infoItem = movieDetail.language,
                title = "Spoken language"
            )
            Spacer(modifier = Modifier.height(Padding.itemSpacing))
            MovieInfoItem(
                infoItem = movieDetail.productionCountry,
                title = "Spoken language"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Reviews",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(Padding.itemSpacing))

            Review(reviews = movieDetail.reviews)
            Spacer(modifier = Modifier.height(Padding.itemSpacing))

            MoreLikeThis(
                fetchMovies = fetchMovies,
                movies = movies,
                isMovieLoading = isMovieLoading,
                onMovieClick = onMovieClick
            )
        }
    }
}

@Composable
fun MoreLikeThis(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
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
            items(movies) {
                MovieCoverImage(movie = it, onMovieClick = onMovieClick)
            }
        }
    }
}

private enum class ActionIcon(val icon: ImageVector, val contentDescription: String) {
    Bookmark(icon = Icons.Default.Bookmark, contentDescription = "Bookmark"),
    Share(icon = Icons.Default.Share, contentDescription = "Share"),
    Download(icon = Icons.Default.Download, contentDescription = "Download")
}

@Composable
private fun ActionIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    backgroundColor: Color = Color.Black.copy(.8f)
) {
    MovieCard(
        shapes = CircleShape,
        modifier = modifier
            .padding(4.dp),
        backgroundColor = backgroundColor
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun MovieInfoItem(
    infoItem: List<String>,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))

        infoItem.forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun Review(
    modifier: Modifier = Modifier,
    reviews: List<Review>
) {
    val (viewMore, setViewMore) = remember {
        mutableStateOf(false)
    }
    val defaultReview = if (reviews.size > 3) reviews.take(3) else reviews
    val movieReviews = if (viewMore) reviews else defaultReview
    val buttonText = if (viewMore) "Collapse" else "More..."

    Column {
        movieReviews.forEach { review ->
            ReviewItem(review = review)
            Spacer(modifier = Modifier.height(Padding.itemSpacing))
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(Padding.itemSpacing))
        }

        TextButton(onClick = { setViewMore(!viewMore) }) {
            Text(
                text = buttonText
            )
        }
    }
}