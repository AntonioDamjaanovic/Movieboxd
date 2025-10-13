package com.example.movieboxxd.ui.person

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.movieboxxd.ui.components.PersonCoverImage
import com.example.movieboxxd.ui.home.components.TopContent
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding

@Composable
fun PersonScreen(
    modifier: Modifier = Modifier,
    personViewModel: PersonViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by personViewModel.personState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = state.error != null,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Padding.default)
            ) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
        AnimatedVisibility(
            visible = !state.isLoading && state.error == null,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor)
            ) {
                state.person?.let { person ->
                    TopContent(
                        title = person.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.12f)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.85f)
                    ) {
                        Column(
                            modifier = Modifier.weight(0.3f)
                                .padding(top = Padding.default, start = Padding.default)
                        ) {
                            PersonCoverImage(person = person)
                        }
                        Column(
                            modifier = Modifier
                                .weight(0.7f)
                                .verticalScroll(rememberScrollState())
                                .padding(start = Padding.default, end = Padding.default, top = Padding.default)
                        ) {
                            Text(
                                text = person.biography,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }


        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp, start = Padding.default)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}