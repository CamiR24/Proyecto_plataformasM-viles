package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                App()
            }
        }
    }
}


@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: "screen1"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Notificaciones",
            modifier = modifier.padding(innerPadding)
        ) {
            /*composable(route = "InicioSesion") { //inicio de sesión
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaInicio(
                        innerPadding = PaddingValues(),
                        navController
                    )
                }
            }

            composable(route = "Registro") { //registro
                //Registro(innerPadding, navController)
            }*/

            composable(route = "Notificaciones") { //notificaciones
                CenterAlignedTopAppBar_Notificaciones(navController)
            }}}}

           /* composable(route = "Registro") { //para ti
                Registro(innerPadding, navController)
            }

            composable(route = "Perfil") {
                //CenterAlignedTopAppBar_Perfil(navController)
            }

            /*composable(route = "Chat") { //chat
                PantallaChat(navController)
            }

            composable(route = "Match") { //match
                PantallaMatch(navController)
            }

            composable(route = "VerMatch") { //visualización de los matches
                PantallaVerMatch(navController)
            }
        }
       }*/
}