package com.example.movieboxxd.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieboxxd.ui.detail.MovieDetailScreen
import com.example.movieboxxd.ui.home.HomeScreen
import com.example.movieboxxd.ui.person.PersonScreen
import com.example.movieboxxd.utils.K

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = Route.Home.route,
            enterTransition = { fadeIn() + scaleIn() },
            exitTransition = { fadeOut() + shrinkOut() }
        ) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.FilmWithArgs.getRoute(movieId)
                    )
                }
            )
        }
        composable(
            route = Route.FilmWithArgs.route,
            arguments = listOf(navArgument(name = K.MOVIE_ID) { type = NavType.IntType })
        ) {
            MovieDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.FilmWithArgs.getRoute(movieId)
                    )
                },
                onActorClick = { personId ->
                    navController.navigate(
                        Route.PersonWithArgs.getRoute(personId)
                    )
                }
            )
        }
        composable(
            route = Route.PersonWithArgs.route,
            arguments = listOf(navArgument(name = K.PERSON_ID) { type = NavType.IntType } )
        ) {
            PersonScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Route.Search.route) { /* SearchScreen() */ }
        composable(Route.Profile.route) { /* ProfileScreen() */ }
    }
}