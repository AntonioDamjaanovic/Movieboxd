package com.example.movieboxxd.ui.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieboxxd.ui.auth.AuthState
import com.example.movieboxxd.ui.theme.BackgroundColor
import com.example.movieboxxd.ui.theme.Padding
import com.example.movieboxxd.ui.theme.SelectedIconColor

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    authState: AuthState,
    onLoginClick: (String, String) -> Unit,
    onAuthenticated: () -> Unit
) {
    LaunchedEffect(authState) {
        if (authState.isLoggedIn) onAuthenticated()
    }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(40.dp)
        ) {
            AppTitle()
            CustomTextField(
                value = username,
                placeholder = "Username",
                imageVector = Icons.Default.Person,
                onValueChange = { username = it }
            )
            CustomTextField(
                value = password,
                placeholder = "Password",
                imageVector = Icons.Default.Password,
                onValueChange = { password = it }
            )
            Button(
                onClick = { onLoginClick(username, password) },
                colors = ButtonDefaults.buttonColors(containerColor = SelectedIconColor),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(Padding.innerButtonPadding),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "LOGIN",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}