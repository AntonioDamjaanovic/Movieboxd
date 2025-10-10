package com.example.movieboxxd.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieboxxd.movie_detail.domain.models.Review
import com.example.movieboxxd.ui.components.CollapsibleText
import com.example.movieboxxd.ui.theme.Padding
import kotlin.math.round

@Composable
fun ReviewItem(
    modifier: Modifier = Modifier,
    review: Review
) {
    Column(modifier = modifier) {
        val nameAnnotatedString = buildAnnotatedString {
            append(review.author)
            append(" \u2020 ")
            append(review.createdAt)
        }
        val ratingAnnotatedString = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append(round(review.rating).toString())
            pop()

            pushStyle(SpanStyle(fontSize = 10.sp))
            append("10")
            pop()
        }

        Text(
            text = nameAnnotatedString,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(Padding.itemSpacing))
        CollapsibleText(
            text = review.content,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(Padding.itemSpacing))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Star,
                contentDescription = null
            )
            Text(
                text = ratingAnnotatedString,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}