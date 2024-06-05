package com.example.artisticanthem

import SearchScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artisticanthem.api.PoemResponse
import com.example.artisticanthem.ui.theme.ArtisticAnthemTheme
import com.example.artisticanthem.ui.theme.Red
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtisticAnthemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavDrawer()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavDrawer() {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .background(Red)
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Text(text = "")
                }
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.HomeScreen.screen) {
                                popUpTo(Screens.HomeScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Settings", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.SettingsScreen.screen) {
                                popUpTo(Screens.SettingsScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Info", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.InfoScreen.screen) {
                                popUpTo(Screens.InfoScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Favorite", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.FavoriteScreen.screen) {
                                popUpTo(Screens.FavoriteScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Search", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.SearchScreen.screen) {
                                popUpTo(Screens.SearchScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Login", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,  // Choose an appropriate icon
                            contentDescription = "Login",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.LoginScreen.screen) {
                                popUpTo(Screens.LoginScreen.screen) { inclusive = true }
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Register", color = Red) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,  // Choose an appropriate icon
                            contentDescription = "Register",
                            tint = Red
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navigationController.navigate(Screens.RegisterScreen.screen) {
                                popUpTo(Screens.RegisterScreen.screen) { inclusive = true }
                            }
                        }
                    })
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "ArtisticAnthem") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Red,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Rounded.Menu, contentDescription = "MenuButton"
                            )
                        }
                    },
                )
            },
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navigationController,
                        startDestination = Screens.HomeScreen.screen
                    ) {
                        composable(Screens.HomeScreen.screen) {
                            HomeScreen(onNavigate = { navigationController.navigate(Screens.SearchScreen.screen) })
                        }
                        composable(Screens.InfoScreen.screen) { InfoScreen() }
                        composable(Screens.SettingsScreen.screen) { SettingsScreen() }
                        composable(Screens.SearchScreen.screen) { SearchScreen(navController = navigationController) }
                        composable(Screens.FavoriteScreen.screen) { FavoriteScreen() }
                        composable(Screens.LoginScreen.screen) {
                            LoginScreen(
                                onLoginSuccess = { navigationController.navigate(Screens.HomeScreen.screen) },
                                onRegisterClick = { navigationController.navigate(Screens.RegisterScreen.screen) }
                            )
                        }
                        composable(Screens.RegisterScreen.screen) {
                            RegisterScreen(
                                onRegisterSuccess = { navigationController.navigate(Screens.HomeScreen.screen) },
                                onLoginClick = { navigationController.popBackStack() }
                            )
                        }
                        composable(
                            route = "details/{poem}",
                            arguments = listOf(navArgument("poem") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val json = backStackEntry.arguments?.getString("poem")
                            val poem = Gson().fromJson(json, PoemResponse::class.java)
                            PoetryDetailsScreen(poetry = poem)
                        }
                    }
                }
            }
        )
    }
}
