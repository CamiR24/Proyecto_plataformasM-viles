package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
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
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily

class Recomendaciones : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                val navController = rememberNavController()
                val innerPadding = PaddingValues()
                RecomendacionesScreen(innerPadding, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecomendacionesScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFbb4491),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color(0xFF54398C)
                ),
                title = {
                    Text(
                        text = stringResource(R.string.Recomendaciones),
                        fontFamily = cocoFontFamily,
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
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navController.navigate("Notificaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier
                            .offset(x=30.dp,y=10.dp)) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { navController.navigate("Perfil") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=70.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { navController.navigate("Chat") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x=120.dp,y=10.dp)) {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { navController.navigate("Recomendaciones") },
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
        Recomendaciones(navController = navController, innerPadding = innerPadding)
    }
}

@Composable
fun Recomendaciones(navController: NavHostController, innerPadding: PaddingValues) {
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
            LazyColumn {
                item {
                    FilaRecomendacion(navController)
                    FilaRecomendacion(navController)
                    FilaRecomendacion(navController)
                    FilaRecomendacion(navController)
                }
            }
        }
    }
}

@Composable
fun FilaRecomendacion(navController: NavHostController) {
    Surface (
        color = Color(0xFFF1E2EC),
        modifier = Modifier
            .height(300.dp)
            .padding(vertical = 10.dp)
    ) {
        Column {
            Text(
                text ="Por Atributo",
                fontFamily = cocoFontFamily,
                color = Color(0xFFBB4491),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp )
            )
            LazyRow (
                modifier = Modifier
                    .fillMaxWidth()
            ){
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

@Composable
fun PerfilRecomendado(navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.leah),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .clipToBounds()
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color(0xFFECCCE2)),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "Nombre,",
                    fontFamily = cocoFontFamily,
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                )
                Text(
                    text = "Atributo",
                    fontFamily = cocoFontFamily,
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.like),
                        contentDescription = "Like",
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .align(Alignment.CenterEnd)
                            .clickable { navController.navigate("Match") }

                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecomendacionesPreview() {
    Proyecto_plataformasMovilesTheme {
        val navController = rememberNavController()
        val innerPadding = PaddingValues()

        RecomendacionesScreen(innerPadding, navController)
    }
}
