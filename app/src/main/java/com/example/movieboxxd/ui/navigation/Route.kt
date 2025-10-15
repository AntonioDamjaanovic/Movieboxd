package com.example.movieboxxd.ui.navigation

sealed class Route(val route: String) {
    object Home : Route("home")
    object MoreMovies: Route("moreMovies/{type}") {
        fun getRoute(type: String) = "moreMovies/$type"
    }

    object Movie : Route("film")
    object MovieWithArgs : Route("film/{id}") {
        fun getRoute(id: Int) = "film/$id"
    }

    object Person: Route("person")
    object PersonWithArgs: Route("person/{id}") {
        fun getRoute(id: Int) = "person/$id"
    }

    object Search : Route("search")
    object Profile : Route("profile")
}