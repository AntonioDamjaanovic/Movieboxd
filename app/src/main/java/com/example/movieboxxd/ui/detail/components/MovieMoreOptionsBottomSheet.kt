package com.example.movieboxxd.ui.detail.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.DefaultColor
import com.example.movieboxxd.ui.theme.Padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieMoreOptionsBottomSheet(
    movieDetail: MovieDetail,
    show: Boolean,
    onDismiss: () -> Unit,
    onWatchClick: () -> Unit,
    onWatchlistClick: (Boolean) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    onRateClick: (Double) -> Unit
) {
    var isWatched by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }
    var isInWatchlist by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableIntStateOf(0) }

    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = DefaultColor
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Padding.default,
                            vertical = Padding.defaultBottomSheet
                        )
                ) {
                    Text(
                        text = movieDetail.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                    Text(
                        text = movieDetail.releaseDate,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.LightGray
                    )
                }
                HorizontalDivider(
                    color = BackgroundColor
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Padding.horizontalSpacing,
                            vertical = Padding.defaultBottomSheet
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = { isWatched = !isWatched }) {
                            Icon(
                                imageVector = if (isWatched) Icons.Filled.Visibility else Icons.Outlined.Visibility,
                                contentDescription = "Watch",
                                modifier = Modifier.size(100.dp),
                                tint = if (isWatched) Color.Yellow else Color.Gray
                            )
                        }
                        Text(
                            text = "Watch",
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = { isFavorite = !isFavorite }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(100.dp),
                                tint = if (isFavorite) Color.Yellow else Color.Gray
                            )
                        }
                        Text(
                            text = "Favorite",
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = { isInWatchlist = !isInWatchlist }) {
                            Icon(
                                imageVector = if (isInWatchlist) Icons.Filled.WatchLater else Icons.Outlined.WatchLater,
                                contentDescription = "Watchlist",
                                modifier = Modifier.size(100.dp),
                                tint = if (isInWatchlist) Color.Yellow else Color.Gray
                            )
                        }
                        Text(
                            text = "Watchlist",
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                HorizontalDivider(
                    color = BackgroundColor
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Padding.verticalSpacing)
                ) {
                    RatingStars(
                        rating = selectedRating,
                        onRatingChange = { selectedRating = it }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Rate",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    onFavoriteClick(isFavorite)
                    onWatchlistClick(isInWatchlist)
                    if (selectedRating > 0) {
                        onRateClick(selectedRating.toDouble() * 2)
                    }
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Apply")
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun RatingStars(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starSize: Dp = 50.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = "Rate $i",
                modifier = Modifier
                    .size(starSize)
                    .clickable { onRatingChange(i) },
                tint = if (i <= rating) Color.Yellow else Color.Gray
            )
        }
    }
}