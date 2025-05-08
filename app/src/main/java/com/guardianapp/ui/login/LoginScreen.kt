package com.guardianapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.guardianapp.ui.theme.*
import com.guardianapp.navigation.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = BackgroundBlue,
            darkIcons = false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Logo
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "G",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome Back!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Email",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("example@gmail.com", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Password",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("LOGIN", fontSize = 16.sp, color = White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign up text and link in one row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You do not have an account? ",
                color = White,
                fontSize = 14.sp
            )
            Text(
                text = "Sign up",
                color = ButtonBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { /* Will be implemented later */ }
                    .padding(start = 4.dp),
                textDecoration = TextDecoration.Underline
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign up Button
        Button(
            onClick = { /* Will be implemented later */ },
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(150.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign up", fontSize = 16.sp, color = White)
        }
    }
}