package com.guardianapp.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.guardianapp.R
import com.guardianapp.ui.theme.*
import kotlinx.coroutines.delay
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    var currentDot by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(300)
            currentDot = (currentDot + 1) % 3
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = BackgroundBlue,
            darkIcons = false
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
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
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "G",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Heart Icon",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color(0xFFE53935))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Guardian",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_medical_file),
                contentDescription = "Medical File Icon",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color(0xFF4CAF50))
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                text = "Loading",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .background(
                                color = if (index == currentDot) White else White.copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "\"Your Safety, Always Accessible ✨\"",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}
