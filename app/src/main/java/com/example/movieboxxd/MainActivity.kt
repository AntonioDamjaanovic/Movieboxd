package com.example.movieboxxd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieboxxd.ui.theme.SelectedIconColor
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieboxxd.ui.navigation.BottomNavigationItem
import com.example.movieboxxd.ui.navigation.NavigationGraph
import com.example.movieboxxd.ui.navigation.Route
import com.example.movieboxxd.ui.theme.DefaultColor
import com.example.movieboxxd.ui.theme.MovieboxxdTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieboxxdTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination?.route

        val bottomNavigationItems = listOf(
            BottomNavigationItem(
                route = Route.Home.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
            BottomNavigationItem(
                route = Route.Search.route,
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search
            ),
            BottomNavigationItem(
                route = Route.Profile.route,
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            )
        )

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = DefaultColor
                ) {
                    bottomNavigationItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (currentDestination == item.route) item.selectedIcon
                                        else item.unselectedIcon,
                                    contentDescription = item.route,
                                    tint = if (currentDestination == item.route) SelectedIconColor else Color.Gray
                                )
                            },
                            selected = currentDestination == item.route,
                            onClick = {
                                if (currentDestination != item.route) {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavigationGraph(
                navController = navController,
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
            )
        }
    }
}