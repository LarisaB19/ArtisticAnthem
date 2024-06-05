package com.example.artisticanthem

sealed class Screens(val screen: String) {
    object HomeScreen: Screens("home")
    object SettingsScreen: Screens("settings")
    object SearchScreen: Screens("search")
    object InfoScreen: Screens("info")
    object FavoriteScreen: Screens("favorite")
    object LoginScreen: Screens("login")
    object RegisterScreen: Screens("register")
    object PoetryDetailsScreen: Screens("details/{poem}") {
        fun createRoute(poem: String): String = "details/$poem"
    }
}
