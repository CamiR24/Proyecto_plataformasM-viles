package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.compose.ui.res.colorResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme

class PantallaNotificaciones : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           BottomAppBarNotificaciones(innerPadding = PaddingValues())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Notificaciones(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Notificaciones",
                        //fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        BottomAppBarNotificaciones(innerPadding)
    }
}

@Composable
fun BottomAppBarNotificaciones(innerPadding: PaddingValues) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier
                            .offset(x=30.dp,y=10.dp)) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* do something */ },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=70.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=120.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=160.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                        )
                    }
                },
                containerColor = Color(0xFFbb4491)
            )
        },
    ) { innerPadding ->
        Notificaciones(innerPadding)
        }
}



@Composable
fun Notificaciones(innerPadding: PaddingValues) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECCCE2))
            .padding(16.dp)
    ) {
        Text(
            text = "Notificaciones",
            fontSize = 40.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.magenta_custom),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .background(Color(0xff8f70c0), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(5.dp)) // Espacio entre el ícono y el texto

            Text(
                text = "¡Woof!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tienes una coincidencia con Max!",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

            Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFd3b8c5), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "¡Guau!",
                color = Color.White, 
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tienes un nuevo mensaje de Daisy",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

            Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .background(Color(0xFFb04a5d), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "¡Conoce a Rocky!",
                color = Color.White, // Personalizar color del texto
                fontSize = 24.sp, // Tamaño de texto
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¡Podrían ser una buena pareja de juegos!",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            //Primer botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable._icon_facebook),
                    contentDescription = "Facebook",
                    //tint = Color.Blue,
                    modifier = Modifier.size(100.dp)
                )
            }

            //Segundo botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable._icon_instagram),
                    contentDescription = "Instagram",
                    //tint = Color.Black,
                    modifier = Modifier.size(100.dp)
                )
                
            }
            //Tercer botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable._icon_google),
                    contentDescription = "Google",
                    //tint = Color.Black,
                    modifier = Modifier.size(100.dp)
                )

            }

            //Cuarto botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_gmail),
                    contentDescription = "Gmail",
                    //tint = Color.Black,
                    modifier = Modifier.size(100.dp)
                )

            }

            //Quinto botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable._icon_tiktok),
                    contentDescription = "TikTok",
                    //tint = Color.DarkGray,
                    modifier = Modifier.size(100.dp)
                )
                
            }

            //Sexto botón
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable._icon_youtube),
                    contentDescription = "YouTube",
                    modifier = Modifier.size(100.dp)
                )
                
            }

        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
    )
@Composable
fun NotificacionesPreview() {

    val navController = rememberNavController()

    Proyecto_plataformasMovilesTheme {
        Notificaciones(innerPadding = PaddingValues())
        BottomAppBarNotificaciones(innerPadding = PaddingValues())
        CenterAlignedTopAppBar_Notificaciones(navController = navController)
    }
}

