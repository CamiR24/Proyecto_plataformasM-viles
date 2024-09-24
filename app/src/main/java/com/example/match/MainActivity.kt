package com.example.match

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.match.ui.theme.MatchTheme
import com.example.proyecto_plataformasmoviles.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogMatchScreen()
        }
    }
}

@Composable
fun DogMatchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFbb4491))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LeahProfileSection()

            MatchSection()

            NextButtonAndClouds()
        }
    }
}

@Composable
fun LeahProfileSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        // Foto de perfil circular
        Image(
            painter = painterResource(id = R.drawable.leahpfp),
            contentDescription = "Leah's Profile Picture",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFb8a0e8)) // Color del círculo
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Nombre de Leah
        Text(
            text = "Leah",
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Composable
fun MatchSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Leah
        CircularProfile("Leah, Westie", R.drawable.leahpfp)

        Text(
            text = "MATCH!",
            fontSize = 36.sp,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        //Oliverio
        CircularProfile("Oliverio, Westie", R.drawable.oliveropfp)
    }
}

@Composable
fun CircularProfile(name: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //img circular
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFb8a0e8)) // Color del círculo
        )
        Spacer(modifier = Modifier.height(8.dp))
        //intento de nombre curvado
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun NextButtonAndClouds() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //boton a siguiente nav
        Button(
            onClick = { /* pam pam a noti o chat? */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
        ) {
            Text(text = "Siguiente", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //revisiones de perfil
        Text(
            text = "Yorki: Milo, Pug: Lucas y Beagle: Tommy... también revisaron tu perfil",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )

        //Nubes de abajo
        Image(
            painter = painterResource(id = R.drawable.clouds),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogMatchScreen()
}
