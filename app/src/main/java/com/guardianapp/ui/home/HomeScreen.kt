package com.guardianapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.guardianapp.R
import com.guardianapp.navigation.Screen

private val GreetingBlue = Color(0xFF90D5FF)

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Hi, Entisar 👋",
                fontSize = 36.sp,
                color = GreetingBlue,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Welcome back to your Guardian app",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            MenuCard(
                icon = R.drawable.ic_medical,
                text = "Medical Info",
                onClick = { navController.navigate(Screen.MedicalInfo.route) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            MenuCard(
                icon = R.drawable.ic_emergency,
                text = "Emergency Contact",
                onClick = { navController.navigate(Screen.EmergencyContact.route) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            MenuCard(
                icon = R.drawable.ic_profile,
                text = "Profile",
                onClick = { navController.navigate(Screen.Profile.route) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            MenuCard(
                icon = R.drawable.ic_logout,
                text = "Logout",
                onClick = { navController.navigate(Screen.Login.route) },
                isLogout = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Next Appointment: Tomorrow at 10 AM",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Blood Type: O+",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


@Composable
private fun MenuCard(
    icon: Int,
    text: String,
    onClick: () -> Unit,
    isLogout: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .shadow(
                    elevation = 30.dp, // Stronger shadow effect
                    shape = RoundedCornerShape(16.dp),
                    spotColor = Color.Black.copy(alpha = 1f), // Higher contrast shadow
                    ambientColor = Color.Black.copy(alpha = 0.7f) // Subtle diffuse light
                ),
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
