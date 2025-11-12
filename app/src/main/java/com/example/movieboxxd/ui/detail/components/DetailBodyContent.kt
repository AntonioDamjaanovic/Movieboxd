package com.example.movieboxxd.ui.detail.components

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.movie.domain.models.Movie
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun DetailBodyContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    similarMovies: List<Movie>,
    isMovieLoading: Boolean,
    fetchMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onPersonClick: (Int) -> Unit
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
                    .padding(bottom = Padding.itemSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cast",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            LazyRow {
                items(movieDetail.cast) {
                    ActorItem(
                        cast = it,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPersonClick(it.id) }
                    )
                    Spacer(modifier = Modifier.width(Padding.default))
                }
            }
            Spacer(modifier = Modifier.height(Padding.verticalSpacing))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Padding.itemSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Crew",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            LazyRow {
                items(movieDetail.crew) {
                    CrewItem(
                        crew = it,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPersonClick(it.id) }
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
                similarMovies = similarMovies,
                isMovieLoading = isMovieLoading,
                onMovieClick = onMovieClick
            )
        }
    }
}