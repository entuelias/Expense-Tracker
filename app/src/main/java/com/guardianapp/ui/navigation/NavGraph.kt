package com.guardianapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guardianapp.ui.emergencycontacts.EmergencyContactScreen
import com.guardianapp.ui.splash.SplashScreen
import com.guardianapp.ui.login.LoginScreen
import com.guardianapp.ui.home.HomeScreen
import com.guardianapp.ui.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.EmergencyContact.route) {
            EmergencyContactScreen(navController = navController)
        }
    }
}