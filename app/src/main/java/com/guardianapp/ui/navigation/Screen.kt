package com.guardianapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object MedicalInfo : Screen("medical_info")
    object EmergencyContact : Screen("emergency_contact")
    object Profile : Screen("profile")
}