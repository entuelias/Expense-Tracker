package com.guardianapp.navigation

import com.guardianapp.ui.signup.SignUpScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guardianapp.ui.emergencycontacts.EmergencyContactScreen
import com.guardianapp.ui.splash.SplashScreen
import com.guardianapp.ui.login.LoginScreen
import com.guardianapp.ui.home.HomeScreen
import com.guardianapp.ui.profile.ProfileScreen

import com.guardianapp.ui.medicalinfo.MedicalInfoScreen
//>>>>>>> Stashed changes
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
        composable("profile") {          // Changed to match string route
            ProfileScreen(navController = navController)
        }

        composable(Screen.EmergencyContact.route) {
            EmergencyContactScreen(navController = navController)
        }
        composable(Screen.MedicalInfo.route) {
            MedicalInfoScreen(navController = navController)
        }
        composable("signup") {           // Changed to match string route
            SignUpScreen(navController = navController)
        }

    }

}


