package com.guardianapp.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.guardianapp.R
import com.guardianapp.ui.theme.BackgroundBlue
import com.guardianapp.ui.theme.PrimaryBlue
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    val isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        delay(2000) // 2 seconds delay
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Guardian Logo
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = PrimaryBlue,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "G",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.surface
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

        // App Icons
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_medical),
                contentDescription = "Medical Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Guardian",
                style = MaterialTheme.typography.headlineMedium
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emergency),
                contentDescription = "Emergency Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tagline
        Text(
            text = "\"Your Safety, Always Accessible\"",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Loading indicator
        if (isLoading) {
            CircularProgressIndicator(
                color = PrimaryBlue,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}
