package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.data.model.Notificacion
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.viewmodel.NotificacionesViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButtonColors
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.MatchesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModel
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModelFactory

class PantallaNotificaciones : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navControler = rememberNavController()
           //BottomAppBarNotificaciones(navController = navControler, innerPadding = PaddingValues())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Notificaciones(
    navController: NavHostController,
    viewModel: NotificacionesViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFbb4491),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color(0xFF54398C)
                ),
                title = {
                    Text(
                        text = "Notificaciones",
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
                },
                scrollBehavior = scrollBehavior,
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
        },
        content = { innerPadding ->
            NotificacionesScreen(innerPadding, navController, viewModel)
        }
    )
}

@Composable
fun NotificacionesScreen(innerPadding: PaddingValues, navController: NavHostController, viewModel: NotificacionesViewModel) {
    val notificaciones by viewModel.notificaciones.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState()

    val perfilesRepository = PerfilesRepository()
    val likesRepository = LikesRepository()
    val matchesRepository = MatchesRepository()
    val factory = PerfilViewModelFactory(perfilesRepository, likesRepository, matchesRepository)
    val perfilViewModel: PerfilViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) {
        viewModel.cargarNotificaciones()
    }

    when {
        isLoading -> LoadingView()
        error != null -> ErrorView(error!!)
        else -> NotificacionesContent(notificaciones, innerPadding)
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color(0xFFbb4491))
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NotificacionesContent(notificaciones: List<com.example.proyecto_plataformasmoviles.data.repository.Notificacion>, innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECCCE2))
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        items(notificaciones) { notificacion ->
            when (notificacion.tipo) {
                1 -> WoofNotification(notificacion)
                2 -> GuauNotification(notificacion)
                else -> DefaultNotification(notificacion)
            }
        }
    }
}

@Composable
fun WoofNotification(notificacion: com.example.proyecto_plataformasmoviles.data.repository.Notificacion) {
    NotificationCard(
        title = "¡Woof!",
        color = Color(0xff8f70c0),
        mensaje = notificacion.mensaje
    )
}

@Composable
fun GuauNotification(notificacion: com.example.proyecto_plataformasmoviles.data.repository.Notificacion) {
    NotificationCard(
        title = "¡Guau!",
        color = Color(0xFFd3b8c5),
        mensaje = notificacion.mensaje
    )
}

@Composable
fun DefaultNotification(notificacion: com.example.proyecto_plataformasmoviles.data.repository.Notificacion) {
    NotificationCard(
        title = "Notificación",
        color = Color.Gray,
        mensaje = notificacion.mensaje
    )
}

@Composable
fun NotificationCard(title: String, color: Color, mensaje: String) {
    Column(
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = mensaje,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun BottomAppBarNotificaciones(navController: NavHostController) {
    BottomAppBar(
        actions = {
            val items = listOf(
                Pair(Icons.Filled.CheckCircle, "Notificaciones"),
                Pair(Icons.Filled.Person, "Perfil/${FirebaseAuth.getInstance().currentUser?.uid}"),
                Pair(Icons.Filled.Email, "Chat"),
                Pair(Icons.Filled.Favorite, "Recomendaciones"),
                Pair(Icons.Filled.Star, "TusMatches")
            )

            items.forEach { (icon, destination) ->
                IconButton(onClick = { navController.navigate(destination) }) {
                    Icon(icon, contentDescription = null)
                }
            }
        },
        containerColor = Color(0xFFbb4491)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificacionesPreview() {
    val dummyNotificaciones = listOf(
        Notificacion(
            id = "1",
            usuarioId = "user1",
            perfilId = "perfil1",
            tipo = 1,
            mensaje = "¡Tienes un Woof!",
            timestamp = System.currentTimeMillis(),
            leido = false
        ),
        Notificacion(
            id = "2",
            usuarioId = "user2",
            perfilId = "perfil2",
            tipo = 2,
            mensaje = "¡Tienes un Guau!",
            timestamp = System.currentTimeMillis(),
            leido = true
        )
    )

    Proyecto_plataformasMovilesTheme {
        //NotificacionesContent(dummyNotificaciones, PaddingValues())
    }
}
