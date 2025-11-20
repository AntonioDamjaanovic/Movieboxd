package com.example.movieboxxd.ui.navigation

sealed class Route(val route: String) {
    object Login: Route("login")

    object Home : Route("home")
    object MoreMovies: Route("moreMovies/{type}") {
        fun getRoute(type: String) = "moreMovies/$type"
    }

    object Movie : Route("movie")
    object MovieWithArgs: Route("movie/{id}") {
        fun getRoute(id: Int) = "movie/$id"
    }

    object Person: Route("person")
    object PersonWithArgs: Route("person/{id}") {
        fun getRoute(id: Int) = "person/$id"
    }

    object Search: Route("search")

    object Profile: Route("profile")
    object ProfileWithArgs: Route("profile/{id}") {
        fun getRoute(id: Int) = "profile/$id"
    }
    object ProfileMovies: Route("profileMovies/{type}") {
        fun getRoute(type: String) = "profileMovies/$type"
    }
}