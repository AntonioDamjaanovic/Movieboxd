package com.example.movieboxxd.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.ui.theme.TopContentColor

@Composable
fun TopContent(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(TopContentColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier,
    rating: Double,
    title: String,
    genre: List<String>
) {
    Column(
        modifier = modifier.padding(Padding.default)
    ) {
        MovieCard {
            Row(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                val roundedString = String.format("%.2f", rating)
                Text(text = roundedString)
            }
        }
        Spacer(modifier = Modifier.height(Padding.itemSpacing))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(Padding.itemSpacing))
        MovieCard {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                genre.forEachIndexed { index, genreText ->
                    if (index != 0) {
                        VerticalDivider(modifier = Modifier.height(16.dp))
                    }
                    Text(
                        text = genreText,
                        modifier = Modifier
                            .padding(6.dp)
                            .weight(1f),
                        maxLines = 1
                    )

                    if (index != genre.lastIndex) {
                        VerticalDivider(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieDetail(modifier: Modifier = Modifier) {
    MovieDetail(
        rating = 7.5,
        title = "Prisoners",
        genre = listOf("Mystery", "Crime", "Thriller")
    )
}