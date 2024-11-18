package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModel
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModelFactory
import com.google.firebase.auth.FirebaseAuth

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
fun RecomendacionesScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
) {
    val perfilesRepository = PerfilesRepository()
    val likesRepository = LikesRepository()
    val factory = PerfilViewModelFactory(perfilesRepository, likesRepository)
    val viewModel: PerfilViewModel = viewModel(factory = factory)

    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(Unit) {
        viewModel.cargarRecomendacionesPorEdad(5)
        viewModel.cargarRecomendacionesPorUbicacion("Guatemala")
        viewModel.cargarRecomendacionesPorRaza("Labrador")
        currentUserId?.let { viewModel.cargarLikesPorUsuario(it) }
    }

    val recomendacionesPorEdad by viewModel.recomendacionesPorEdad.collectAsState()
    val recomendacionesPorUbicacion by viewModel.recomendacionesPorUbicacion.collectAsState()
    val recomendacionesPorRaza by viewModel.recomendacionesPorRaza.collectAsState()
    val likesPorUsuario by viewModel.likesPorUsuario.observeAsState(emptyList())

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
                        text = stringResource(R.string.Recomendaciones),
                        fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("Ajustes") }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Ajustes"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(
                        onClick = { navController.navigate("Notificaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x = 25.dp, y = 10.dp)
                    ) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = "Notificaciones")
                    }
                    IconButton(
                        onClick = {
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            if (userId != null) {
                                navController.navigate("Perfil/$userId")
                            } else {
                                Log.e("BottomBar", "No se encontró el ID del usuario autenticado")
                            }
                        },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x = 50.dp, y = 10.dp)
                    ) {
                        Icon(Icons.Filled.Person, contentDescription = "Perfil")
                    }
                    IconButton(
                        onClick = { navController.navigate("Chat") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x = 75.dp, y = 10.dp)
                    ) {
                        Icon(Icons.Filled.Email, contentDescription = "Chat")
                    }
                    IconButton(
                        onClick = { navController.navigate("Recomendaciones") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x = 100.dp, y = 10.dp)
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Recomendaciones")
                    }
                    IconButton(
                        onClick = { navController.navigate("TusMatches") },
                        colors = IconButtonColors(Color(0xFFbb4491), Color(0xFF54398c), Color(0xFF54398c), Color(0xFF54398c)),
                        modifier = Modifier.offset(x = 125.dp, y = 10.dp)
                    ) {
                        Icon(Icons.Filled.Star, contentDescription = "Tus Matches")
                    }
                },
                containerColor = Color(0xFFbb4491)
            )
        }
    ) { innerPadding ->
        Recomendaciones(
            navController = navController,
            innerPadding = innerPadding,
            recomendacionesPorEdad = recomendacionesPorEdad,
            recomendacionesPorUbicacion = recomendacionesPorUbicacion,
            recomendacionesPorRaza = recomendacionesPorRaza,
            likesPorUsuario = likesPorUsuario,
            viewModel = viewModel,
            currentUserId = currentUserId.orEmpty()
        )
    }
}

@Composable
fun Recomendaciones(
    navController: NavHostController,
    innerPadding: PaddingValues,
    recomendacionesPorEdad: List<Perfil>,
    recomendacionesPorUbicacion: List<Perfil>,
    recomendacionesPorRaza: List<Perfil>,
    likesPorUsuario: List<String>,
    viewModel: PerfilViewModel,
    currentUserId: String
) {
    Surface(
        color = Color(0xFFECCCE2),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                    FilaRecomendacion(navController, "Por Edad", recomendacionesPorEdad, likesPorUsuario, viewModel, currentUserId)
                    FilaRecomendacion(navController, "Por Ubicación", recomendacionesPorUbicacion, likesPorUsuario, viewModel, currentUserId)
                    FilaRecomendacion(navController, "Por Raza", recomendacionesPorRaza, likesPorUsuario, viewModel, currentUserId)
                }
            }
        }
    }
}

@Composable
fun FilaRecomendacion(
    navController: NavHostController,
    titulo: String,
    perfiles: List<Perfil>,
    likesPorUsuario: List<String>,
    viewModel: PerfilViewModel,
    currentUserId: String
) {
    Surface(
        color = Color(0xFFF1E2EC),
        modifier = Modifier
            .height(300.dp)
            .padding(vertical = 10.dp)
    ) {
        Column {
            Text(
                text = titulo,
                fontFamily = cocoFontFamily,
                color = Color(0xFFBB4491),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(perfiles) { perfil ->
                    PerfilRecomendado(
                        navController = navController,
                        perfil = perfil,
                        isLiked = likesPorUsuario.contains(perfil.usuario_id),
                        onLikeToggle = { isLiked ->
                            viewModel.toggleLike(currentUserId, perfil.usuario_id, isLiked)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PerfilRecomendado(
    navController: NavHostController,
    perfil: Perfil,
    isLiked: Boolean,
    onLikeToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .width(200.dp)
            .clickable {
                navController.navigate("Perfil/${perfil.usuario_id}")
            },
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
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color(0xFFECCCE2)),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = perfil.nombre_del_perro,
                    fontFamily = cocoFontFamily,
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                )
                Text(
                    text = perfil.ubicacion,
                    fontFamily = cocoFontFamily,
                    color = Color(0xFFBB4491),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.Gray,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                onLikeToggle(!isLiked)
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecomendacionesPreview() {
    val testRepository = PerfilesRepository()

    Proyecto_plataformasMovilesTheme {
        val navController = rememberNavController()
        val innerPadding = PaddingValues()

        RecomendacionesScreen(innerPadding, navController)
    }
}

