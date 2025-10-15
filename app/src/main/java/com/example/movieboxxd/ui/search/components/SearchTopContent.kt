package com.example.movieboxxd.ui.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieboxxd.ui.search.SearchViewModel
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.ui.theme.TopContentColor

@Composable
fun SearchTopContent(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel
) {
    val query by searchViewModel.searchText.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(TopContentColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.default),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchInputField(
                query = query,
                onQueryChange = { query ->
                    searchViewModel.onSearchTextChange(query)
                }
            )
        }
    }
}