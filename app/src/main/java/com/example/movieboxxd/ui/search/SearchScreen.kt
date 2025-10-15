package com.example.movieboxxd.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.search.components.SearchBodyContent
import com.example.movieboxxd.ui.search.components.SearchTopContent
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val state by searchViewModel.searchState.collectAsStateWithLifecycle()

    Box(modifier = modifier) {
        AnimatedVisibility(visible = state.error != null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(Padding.default)
            ) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchTopContent(
                    modifier = Modifier.height(160.dp),
                    searchViewModel = searchViewModel
                )
                SearchBodyContent(
                    movies = state.movies,
                    isLoading = state.isLoading,
                    onMovieClick = onMovieClick
                )
            }
        }
        LoadingView(isLoading = state.isLoading)
    }
}