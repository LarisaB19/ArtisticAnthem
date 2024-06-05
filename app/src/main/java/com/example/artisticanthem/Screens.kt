package com.example.artisticanthem

sealed class Screens(val screen: String) {
    data object HomeScreen:Screens("home")
    data object SettingsScreen: Screens("settings")
    data object SearchScreen: Screens("search")
    data object InfoScreen: Screens("info")
    data object FavoriteScreen: Screens("favorite")

}