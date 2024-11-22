package com.example.match

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_plataformasmoviles.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository

class MatchScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            //DogMatchScreen(navController = navController)
        }
    }
}
/*
renderiza toda la pantalla cuando 2 perros hacen "match"
-Parametros:
navController: default de navegacion para cambiar de pantallas
userProfile: informacion del perfil del perro del usuario
matchedProfile: informacion del perfil del perro con el que hizo "match"
otherViewers: lista de otros perfiles que revisaron el perfil del usuario.
-Estructura:
Usa un box para el fondo y organiza elementos en un Column:
LeahProfileSection: perfil del usuario
MatchSection: perfil del usuario y el del "match" con un el texto de "MATCH!"
NextButtonAndClouds: botón para continuar y una imagen decorativa de nubes
 */
@Composable
fun DogMatchScreen(navController: NavController, usuario1: String, usuario2: String) {
    val perfilesRepository = PerfilesRepository()

    val userProfile = remember { mutableStateOf<Perfil?>(null) }
    val matchedProfile = remember { mutableStateOf<Perfil?>(null) }

    // Cargar perfiles al iniciar
    LaunchedEffect(usuario1, usuario2) {
        val userProfileResult = perfilesRepository.obtenerPerfilPorUsuario(usuario1)
        val matchedProfileResult = perfilesRepository.obtenerPerfilPorUsuario(usuario2)

        userProfile.value = userProfileResult.getOrNull()
        matchedProfile.value = matchedProfileResult.getOrNull()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Fondo))
    ) {
        if (userProfile.value != null && matchedProfile.value != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LeahProfileSection(userProfile.value!!)
                MatchSection(userProfile.value!!, matchedProfile.value!!)
                Spacer(modifier = Modifier.height(0.dp))
                NextButtonAndClouds(navController, listOf()) // Puedes añadir otros viewers si es necesario
            }
        } else {
            Text("Cargando...", modifier = Modifier.align(Alignment.Center))
        }
    }
}

/*
muestra la sección con la imagen y el nombre del perro del usuario (esquina superior izquireda).
- Maneja casos si el nombre si tiene más de 10 caracteres
- Usa un Row para alinear la imagen circular y el nombre
- Carga la imagen del perro desde una URL
 */
@Composable
fun LeahProfileSection(userProfile: Perfil) {
    val truncatedName = if (userProfile.nombre_del_perro.length > 10) {
        userProfile.nombre_del_perro.take(10) + "..."
    } else {
        userProfile.nombre_del_perro
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.leah),
            contentDescription = "${userProfile.nombre_del_perro}'s Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFb8a0e8))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = truncatedName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

/*
Muestra el perfil del usuario y el perfil del perro con el que hizo "match"
- Renderiza ambos perfiles lado a lado
- Muestra el texto "MATCH!"
 */
@Composable
fun MatchSection(userProfile: Perfil, matchedProfile: Perfil) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProfile(
                name = userProfile.nombre_del_perro,
                breed = userProfile.raza_del_perro,
                imageUrl = userProfile.imagen
            )
            CircularProfile(
                name = matchedProfile.nombre_del_perro,
                breed = matchedProfile.raza_del_perro,
                imageUrl = matchedProfile.imagen
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

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

/*
Renderiza un perfil de perro en formato circular
- Muestra el nombre, la raza y la imagen del perro
- La imagen está contenida en un contenedor circular con fondo decorativo
- Maneja simetria por estética y que no haya un overflow de caracteres en el nombre o la raza del perro
 */
@Composable
fun CircularProfile(name: String, breed: String, imageUrl: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .width(150.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(60.dp)
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = breed,
                fontSize = 25.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.dog2),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.circlePurple))
        )
    }
}

/*
Muestra un botón para avanzar y una imagen de nubes
- Muestra un mensaje indicando quiénes revisaron el perfil
- Parte de la navegacion a otra pantalla
 */
@Composable
fun NextButtonAndClouds(navController: NavController, otherViewers: List<Perfil>) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Button(
                onClick = { navController.navigate("Recomendaciones") },
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

        if (otherViewers.isNotEmpty()) {
            val viewerText = otherViewers.joinToString(", ") { "${it.raza_del_perro}: ${it.nombre_del_perro}" }
            val finalText = if (viewerText.length > 50) {
                "Varios usuarios... también revisaron tu perfil"
            } else {
                "$viewerText... también revisaron tu perfil"
            }

            Text(
                text = finalText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    //DogMatchScreen(navController = navController)
}

