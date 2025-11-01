package com.example.movieboxxd.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieboxxd.person.domain.models.Person
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.utils.DBConstants

@Composable
fun PersonCoverImage(
    modifier: Modifier = Modifier,
    person: Person,
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${DBConstants.BASE_IMAGE_URL}${person.profilePath}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier
            .size(width = 140.dp, height = 200.dp)
            .padding(Padding.itemSpacing)
    ) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.small)
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop
        )
    }
}