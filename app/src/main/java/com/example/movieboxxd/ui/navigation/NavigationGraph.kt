package com.example.movieboxxd.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieboxxd.ui.detail.MovieDetailScreen
import com.example.movieboxxd.ui.home.HomeScreen
import com.example.movieboxxd.ui.home.MoreMoviesScreen
import com.example.movieboxxd.ui.person.PersonScreen
import com.example.movieboxxd.ui.search.SearchScreen
import com.example.movieboxxd.utils.K

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
        modifier = modifier.fillMaxSize(),
        enterTransition = { fadeIn(animationSpec = tween(100))},
        exitTransition = { fadeOut(animationSpec = tween(100)) },
        popEnterTransition = { fadeIn(animationSpec = tween(100)) },
        popExitTransition = { fadeOut(animationSpec = tween(100)) }
    ) {
        composable(
            route = Route.Home.route
        ) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieWithArgs.getRoute(movieId)
                    )
                },
                onMoreMoviesClick = { title ->
                    navController.navigate(Route.MoreMovies.getRoute(title))
                }
            )
        }
        composable(
            route = Route.MoreMovies.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "empty"

            MoreMoviesScreen(
                type = type,
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieWithArgs.getRoute(movieId)
                    )
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Route.MovieWithArgs.route,
            arguments = listOf(navArgument(name = K.MOVIE_ID) { type = NavType.IntType })
        ) {
            MovieDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieWithArgs.getRoute(movieId)
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

        composable(Route.Search.route) {
            SearchScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieWithArgs.getRoute(movieId)
                    )
                }
            )
        }
        composable(Route.Profile.route) { /* ProfileScreen() */ }
    }
}