package com.example.movieboxxd.ui.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.movieboxxd.R
import com.example.movieboxxd.movie_detail.domain.models.MovieDetail
import com.example.movieboxxd.utils.DB

@Composable
fun DetailTopContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${DB.BASE_IMAGE_URL}${movieDetail.backdropPath}")
        .crossfade(true)
        .build()

    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.movies_background)
        )
    }
}