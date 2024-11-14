package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.match.DogMatchScreen
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModel
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModelFactory

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
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "InicioSesion",
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = "InicioSesion") {
                InicioSesion(navController, authViewModel = AuthViewModel())
            }

            composable(route = "Registro") {
                Registro(innerPadding, navController, authViewModel = AuthViewModel())
            }

            composable(route = "Notificaciones") {
                CenterAlignedTopAppBar_Notificaciones(navController)
            }

            composable(route = "Perfil") {
                CenterAlignedTopAppBar_Perfil(navController)
            }

            composable(route = "Recomendaciones") {
                RecomendacionesScreen(innerPadding, navController)
            }

            composable(route = "Chat") {
                ChatScreen(navController)
            }

            composable(route = "Match") {
                DogMatchScreen(navController)
            }

            composable(route = "Ajustes") {
                CenterAlignedTopAppBar_Ajustes(navController)
            }

            composable(route = "TusMatches") {
                MostrarMatchesScreen(innerPadding, navController)
            }
        }
    }
}
