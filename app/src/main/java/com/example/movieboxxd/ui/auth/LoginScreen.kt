package com.example.movieboxxd.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movieboxxd.ui.auth.components.LoginForm
import com.example.movieboxxd.ui.components.ErrorView
import com.example.movieboxxd.ui.components.LoadingView
import com.example.movieboxxd.ui.theme.BackgroundColor

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    authState: AuthState,
    onLoginClick: (String, String) -> Unit,
    onAuthenticated: () -> Unit
) {
    Box(modifier = modifier) {
        when {
            authState.isLoading -> LoadingView()
            else -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    LoginForm(
                        authState = authState,
                        onLoginClick = onLoginClick,
                        onAuthenticated = onAuthenticated
                    )
                }
            }
        }
    }
}