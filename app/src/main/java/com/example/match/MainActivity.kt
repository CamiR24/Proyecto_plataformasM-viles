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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.match.ui.theme.MatchTheme
import com.example.proyecto_plataformasmoviles.R

//Puntos a trabajar:
//El espacio entre MATCH! y el boton de siguiente debe ser menor y deben estar relativamente cera
//Los nombres de ambos perros deben de estar arriba de las imagenes redondeadas de perfil curbadas como un arcoiris
//El Texto de "Yorki: Milo, Pug: Lucas y Beagle: Tommy... también revisaron tu perfil" debe estar más abajo casi tocando el fondo de la pantalla con el fondo blancod e las nubes para que contraste bien con el color negro de su texto



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

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.padding(top = 18.dp)) { //Distancia entre dos circulos y MATCH {
            Text(
                text = "MATCH!",
                fontSize = 66.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .offset(x = 6.dp, y = 6.dp) // Adjust shadow position
                    .background(Color.Black, shape = RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )

            Text(
                text = "MATCH!",
                fontSize = 66.sp,
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
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.circlePurple))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun NextButtonAndClouds() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            //"Siguiente"
            Button(
                onClick = { /* a noti o chat */ },
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
                    fontSize = 32.sp
                )
            }

            Spacer(modifier = Modifier.height(0.dp))

            Image(
                painter = painterResource(id = R.drawable.clouds),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp), //altura nubes
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = "Yorki: Milo, Pug: Lucas y Beagle: Tommy... también revisaron tu perfil",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogMatchScreen()
}
