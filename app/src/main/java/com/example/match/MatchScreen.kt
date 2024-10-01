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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_plataformasmoviles.R
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DogMatchScreen(navController = navController)
        }
    }
}

@Composable
fun DogMatchScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Fondo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LeahProfileSection()
            MatchSection()
            NextButtonAndClouds(navController)
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
        Image(
            painter = painterResource(id = R.drawable.leahpfp),
            contentDescription = "Leah's Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFb8a0e8))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Leah",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun MatchSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProfile("Leah, Westie", R.drawable.leahpfp)
            CircularProfile("Oliverio, Westie", R.drawable.oliveropfp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.padding(top = 6.dp)) {
            Text(
                text = "MATCH!",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .offset(x = 6.dp, y = 6.dp)
                    .background(Color.Black, shape = RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )

            Text(
                text = "MATCH!",
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun CircularProfile(name: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 0f //!curvas
                }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.circlePurple))
        )
    }
}

@Composable
fun NextButtonAndClouds(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            //"Siguiente"
            Button(
                onClick = { navController.navigate("Perfil") },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.petPurple)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Siguiente",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(id = R.drawable.clouds),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = "Yorki: Milo, Pug: Lucas y Beagle: Tommy... también revisaron tu perfil",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    DogMatchScreen(navController = navController)
}
