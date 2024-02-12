package com.example.demoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.list.presentation.carousel_screen.CarouselScreen
import com.example.list.presentation.home_screen.HomeScreen
import com.example.list.presentation.list_screen.ListScreen

sealed class DestinationScreen(val route: String) {
    object HomeScreen : DestinationScreen("Home")
    object CarouselScreen : DestinationScreen("Carousel")
    object ListScreen : DestinationScreen("ListScreen")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationScreen.HomeScreen.route) {
        composable(DestinationScreen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(DestinationScreen.CarouselScreen.route) {
            CarouselScreen(navController)
        }
        composable(DestinationScreen.ListScreen.route) {
            ListScreen(navController)
        }
    }
}

fun navigateTo(navController: NavController, destinationScreen: DestinationScreen) {
    navController.navigate(destinationScreen.route) {
        launchSingleTop = true
    }
}