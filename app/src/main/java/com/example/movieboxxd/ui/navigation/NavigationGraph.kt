package com.example.movieboxxd.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieboxxd.ui.auth.AuthViewModel
import com.example.movieboxxd.ui.auth.LoginScreen
import com.example.movieboxxd.ui.detail.MovieDetailScreen
import com.example.movieboxxd.ui.home.HomeScreen
import com.example.movieboxxd.ui.home.MoreMoviesScreen
import com.example.movieboxxd.ui.person.PersonScreen
import com.example.movieboxxd.ui.search.SearchScreen
import com.example.movieboxxd.utils.DBConstants

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
   val authState by authViewModel.authState.collectAsStateWithLifecycle()

    LaunchedEffect(authState) {
        if (!authState.isLoggedIn) {
            navController.navigate(Route.Login.route) {
                popUpTo(0)
                launchSingleTop = true
            }
        }
    }

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
            route = Route.Login.route
        ) {
            LoginScreen(
                authState = authState,
                onLoginClick = { username, password ->
                    authViewModel.login(username, password)
                },
                onAuthenticated = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = Route.Home.route
        ) {
            if (!authState.isLoggedIn) {
                LaunchedEffect(Unit) {
                    navController.navigate(Route.Login.route) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
                return@composable
            }

            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieWithArgs.getRoute(movieId)
                    )
                },
                onMoreMoviesClick = { type ->
                    navController.navigate(Route.MoreMovies.getRoute(type))
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
            arguments = listOf(navArgument(name = DBConstants.MOVIE_ID) { type = NavType.IntType })
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
            arguments = listOf(navArgument(name = DBConstants.PERSON_ID) { type = NavType.IntType } )
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