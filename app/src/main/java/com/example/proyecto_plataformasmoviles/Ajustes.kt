package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily

class Ajustes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Ajustes(innerPadding = PaddingValues(), navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Ajustes(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarColors(Color(0xFFbb4491), Color(0xFFbb4491), Color(0xFF54398c), Color(0xFFFFFFFF), Color(0xFF54398c)
                ),
                title = {
                    Text(
                        text = stringResource(R.string.Perfil),
                        fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("Recomendaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFFFFFFFF), Color(0xFF54398c), Color(0xFF54398c)),) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Ajustes(innerPadding, navController)
    }
}

@Composable
fun Ajustes(innerPadding: PaddingValues, navController: NavHostController) {
    Column (
        Modifier
            .padding(innerPadding)
            .offset(y = 0.6.dp)
            .background(Color(0xFFF3F3F3))
            .fillMaxHeight()
    ) {
        MenuItem(title = "Edit profile", subtitle = "", Icons.Outlined.Person, Color(0xFFbb4491))
        MenuItem(title = "Email Adresses", subtitle = "", Icons.Outlined.Email, Color(0xFF54398c))
        MenuItem(title = "Notifications", subtitle = "", Icons.Outlined.Notifications, Color(0xFFbb4491))
        MenuItem(title = "Privacy", subtitle = "", Icons.Outlined.Lock, Color(0xFF54398c))
        Spacer(modifier = Modifier.height(10.dp))
        MenuItem(title = "Help & Feedback", subtitle = "Troubleshooting tips and guides", Icons.Outlined.AccountBox, Color(0xFFbb4491))
        MenuItem(title = "About", subtitle = "App information and documents", Icons.Outlined.Person, Color(0xFF54398c))
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextButton(onClick = { navController.navigate("InicioSesion") },
                Modifier.fillMaxWidth()
            ) {
                Text("Logout", fontSize = 18.sp, color = Color.Red)
            }
        }
    }
}

@Composable
fun MenuItem(title: String, subtitle: String, icon: ImageVector, iconColor: Color) {
    Row (
        modifier = Modifier
            .padding(vertical = 0.6.dp)
            .background(Color.White)
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = icon,
            contentDescription = "Menu item",
            modifier = Modifier
                .size(40.dp),
            tint = iconColor
        )
        Column (modifier = Modifier.padding(horizontal = 15.dp)) {
            Text(title, fontSize = 19.sp)
            if (subtitle.isNotEmpty()) {
                Text(subtitle, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun AjustesPreview() {
    val navController = rememberNavController()

    Proyecto_plataformasMovilesTheme {
        Ajustes(innerPadding = PaddingValues(), navController = navController)
        CenterAlignedTopAppBar_Ajustes(navController = navController)
    }
}


