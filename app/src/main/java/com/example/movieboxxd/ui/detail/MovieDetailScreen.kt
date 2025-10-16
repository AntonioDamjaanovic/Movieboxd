package com.example.movieboxxd.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.ErrorView
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.detail.components.DetailBodyContent
import com.example.movieboxxd.ui.detail.components.DetailTopContent
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit
) {
    val state by detailViewModel.detailState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxWidth()) {
        when {
            state.isLoading -> {
                LoadingView()
            }
            state.error != null -> {
                ErrorView(errorMessage = state.error ?: "Unknown error")
            }
            else -> {
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val boxHeight = maxHeight
                    val topItemHeight = boxHeight * .3f
                    val bodyItemHeight = boxHeight * .7f

                    state.movieDetail?.let { movieDetail ->
                        DetailTopContent(
                            movieDetail = movieDetail,
                            modifier = Modifier
                                .height(topItemHeight)
                                .align(Alignment.TopCenter)
                        )
                        DetailBodyContent(
                            movieDetail = movieDetail,
                            movies = state.movies,
                            fetchMovies = detailViewModel::fetchMovies,
                            isMovieLoading = state.isMovieLoading,
                            onMovieClick = onMovieClick,
                            onPersonClick = onActorClick,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .height(bodyItemHeight)
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 40.dp, start = Padding.default)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 40.dp, end = Padding.default)
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More options",
                tint = Color.White
            )
        }
    }
}