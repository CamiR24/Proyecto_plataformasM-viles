package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

class PantallaMostrarMatches : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                val navController = rememberNavController()
                val innerPadding = PaddingValues()
                MostrarMatchesScreen(innerPadding, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarMatchesScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFbb4491),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text(
                        text = stringResource(R.string.TusMatches),
                        fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("Ajustes") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFFFFFFFF), Color(0xFF54398c), Color(0xFF54398c))) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navController.navigate("Notificaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier
                            .offset(x=25.dp,y=10.dp)) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { navController.navigate("Perfil") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier
                            .offset(x=50.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { navController.navigate("Chat") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=75.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { navController.navigate("Recomendaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=100.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { navController.navigate("TusMatches") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier
                            .offset(x=125.dp,y=10.dp)) {
                        Icon(Icons.Filled.Star, contentDescription = "Localized description")
                    }
                },
                containerColor = Color(0xFFbb4491)
            )
        },
    ) { innerPadding ->
        MostrarMatches(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
fun MostrarMatches(navController: NavHostController, innerPadding: PaddingValues) {
    Surface(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        color = Color(0xFFECCCE2)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Para Ti",
                fontFamily = cocoFontFamily,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBB4491),
            )
            LazyColumn (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1E2EC))) {
                item {
                    PerfilRecomendado(navController)
                    PerfilRecomendado(navController)
                    PerfilRecomendado(navController)
                    PerfilRecomendado(navController)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MostrarMatchesPreview() {
    Proyecto_plataformasMovilesTheme {
        val navController = rememberNavController()
        val innerPadding = PaddingValues()

        MostrarMatchesScreen(innerPadding, navController)
    }
}
