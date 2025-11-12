package com.example.movieboxxd.ui.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieboxxd.profile.domain.models.Profile
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.utils.DB

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    profile: Profile,
    width: Dp = 120.dp,
    height: Dp = 180.dp
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("${DB.BASE_IMAGE_URL}${profile.avatar.tmdb.avatarPath}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .padding(Padding.itemSpacing)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .matchParentSize()
                .clip(CircleShape)
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop,
        )
    }
}