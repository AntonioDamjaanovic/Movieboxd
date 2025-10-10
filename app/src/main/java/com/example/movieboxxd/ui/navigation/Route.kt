package com.example.movieboxxd.ui.navigation

import com.example.movieboxxd.utils.K

sealed class Route(val route: String) {
    object Home : Route("home")
    object Search : Route("search")
    object Profile : Route("profile")
    object Film : Route("film")
    object FilmWithArgs : Route("film/{id}") {
        fun getRoute(id: Int) = "film/$id"
    }
}