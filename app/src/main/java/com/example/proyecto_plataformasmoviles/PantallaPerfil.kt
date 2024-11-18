package com.example.proyecto_plataformasmoviles

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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

class PantallaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                val navController = rememberNavController()
                CenterAlignedTopAppBar_Perfil(navController = navController, userId = "")
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Perfil(navController: NavHostController, userId: String?) {
    val perfilesRepository = PerfilesRepository()
    val likesRepository = LikesRepository()
    val factory = PerfilViewModelFactory(perfilesRepository, likesRepository)
    val viewModel: PerfilViewModel = viewModel(factory = factory)

    val perfilUsuario by viewModel.perfilUsuario.collectAsState()
    val likesPorUsuario by viewModel.likesPorUsuario.observeAsState(emptyList())

    // Estado para determinar si el perfil está likeado
    var isLiked by remember(perfilUsuario?.usuario_id, likesPorUsuario) {
        mutableStateOf(perfilUsuario?.usuario_id?.let { likesPorUsuario.contains(it) } ?: false)
    }

    // Observa el recuento de likes dinámicamente
    val likeCount = perfilUsuario?.usuario_id?.let {
        viewModel.contarLikes(it).observeAsState(initial = 0)
    } ?: mutableStateOf(0)

    // Cargar los datos del perfil y likes usando el userId recibido
    LaunchedEffect(userId) {
        if (!userId.isNullOrEmpty()) {
            viewModel.cargarPerfilUsuario(userId)
            viewModel.cargarLikesPorUsuario(userId)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFbb4491),
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        text = stringResource(R.string.Perfil),
                        fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold
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
            BottomAppBarPerfil(navController = navController)
        }
    ) { innerPadding ->
        Perfil(
            innerPadding = innerPadding,
            perfilUsuario = perfilUsuario,
            isLiked = isLiked,
            likeCount = likeCount.value,
            onLikeToggle = {
                perfilUsuario?.usuario_id?.let { perfilId ->
                    if (isLiked) {
                        viewModel.toggleLike(userId.orEmpty(), perfilId, false) // Quitar like
                    } else {
                        viewModel.toggleLike(userId.orEmpty(), perfilId, true) // Dar like
                    }
                    isLiked = !isLiked
                }
            }
        )
    }
}

@Composable
fun BottomAppBarPerfil(navController: NavHostController) {
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

@Composable
fun Perfil(
    innerPadding: PaddingValues,
    perfilUsuario: Perfil?,
    isLiked: Boolean,
    likeCount: Int,
    onLikeToggle: () -> Unit
) {
    Surface(color = Color(0xFFECCCE2)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(modifier = Modifier.offset(x = 40.dp, y = 120.dp)) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Icono de usuario",
                    modifier = Modifier.size(35.dp)
                )

                Text(
                    text = perfilUsuario?.nombre_del_perro ?: "Nombre no disponible",
                    fontFamily = cocoFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = 10.dp, y = 8.dp),
                    fontSize = 23.sp
                )
            }

            Image(
                painter = painterResource(R.drawable.leah), // Imagen de ejemplo
                contentDescription = null,
                modifier = Modifier
                    .size(380.dp)
                    .wrapContentSize()
                    .offset(x = 4.dp, y = 140.dp)
            )

            Row(
                modifier = Modifier
                    .offset(x = 40.dp, y = 157.dp)
                    .clickable { onLikeToggle() }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (isLiked) Color.Red else Color.Gray,
                    contentDescription = if (isLiked) "Unlike" else "Like",
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = 20.dp, y = -5.dp)
                )
                Text(
                    text = "$likeCount",
                    modifier = Modifier.offset(x = 22.dp, y = -5.dp),
                    fontSize = 15.sp
                )
            }

            // Descripción dinámica
            val descripcion = perfilUsuario?.let { perfil ->
                val genero = if (perfil.sexo.equals("hembra", ignoreCase = true)) "una hembra" else "un macho"
                val pedigreeTexto = if (perfil.pedigree) "tiene" else "no tiene"
                val antecedentes = if (perfil.antecedentes.isNotEmpty()) {
                    perfil.antecedentes
                } else {
                    "No tiene enfermedades"
                }
                val parejaTexto = if (perfil.pareja) "ha tenido pareja con anterioridad" else "no ha tenido pareja con anterioridad"

                stringResource(
                    R.string.Descripcion,
                    perfil.nombre_del_perro,
                    genero,
                    perfil.raza_del_perro,
                    perfil.ubicacion,
                    perfil.edad_del_perro,
                    pedigreeTexto,
                    antecedentes,
                    parejaTexto
                )
            } ?: stringResource(R.string.Descripcion, "Descripción no disponible", "", "", "", 0, "", "", "")

            Text(
                text = descripcion,
                fontSize = 15.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 0.dp, y = 160.dp)
                    .padding(50.dp, 0.dp)
            )
        }
    }
}
