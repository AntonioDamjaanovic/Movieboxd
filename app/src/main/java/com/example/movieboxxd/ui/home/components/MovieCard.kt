package com.example.movieboxxd.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    shapes: CornerBasedShape = MaterialTheme.shapes.large,
    backgroundColor: Color = Color.Black.copy(.8f),
    content: @Composable () -> Unit
) {
    Card(
        shape = shapes,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieCard() {
    MovieCard {
        Text(
            text = "Action", modifier = Modifier.padding(Padding.itemSpacing)
        )
    }
}