package com.example.movieboxxd.ui.search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.ui.theme.DefaultColor
import com.example.movieboxxd.ui.theme.TopContentColor

@Composable
fun SearchInputField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Search...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        },
        textStyle = MaterialTheme.typography.titleLarge,
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = TopContentColor,
            unfocusedContainerColor = DefaultColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevSearchInputField(
    modifier: Modifier = Modifier
) {
    SearchInputField(
        query = "Interstellar",
        onQueryChange = {}
    )
}