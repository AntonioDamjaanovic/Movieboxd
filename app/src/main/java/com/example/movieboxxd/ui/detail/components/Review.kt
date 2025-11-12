package com.example.movieboxxd.ui.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.movieboxxd.movie_detail.domain.models.Review
import com.example.movieboxxd.ui.theme.Padding
import kotlin.collections.forEach

@Composable
fun Review(
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