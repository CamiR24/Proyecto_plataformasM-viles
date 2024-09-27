package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily

class PantallaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Perfil(
                        innerPadding = PaddingValues())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Perfil(navController: NavHostController) {
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
                        text = stringResource(R.string.Perfil),
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
    ) { innerPadding ->
        BottomAppBarPerfil(innerPadding)
    }
}

@Composable
fun BottomAppBarPerfil(innerPadding: PaddingValues) {
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
        Perfil(innerPadding)
    }
}

@Composable
fun Perfil(innerPadding: PaddingValues) {
    Surface(color = Color(0xFFECCCE2)){
        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.offset(x = 40.dp, y = 120.dp)){
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(35.dp)
                )

                Text(text = stringResource(R.string.Nombre),
                    fontFamily = cocoFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = 10.dp, y = 8.dp),
                    fontSize = 23.sp)
            }

            Image(
                painter = painterResource(R.drawable.leah), //usa imagen importada
                contentDescription = null,
                modifier = Modifier
                    .size(380.dp)
                    .wrapContentSize()
                    .offset(x = 4.dp, y = 140.dp)
            )

            Row(modifier = Modifier.offset(x = 40.dp, y = 55.dp)){
                Image(
                    painter = painterResource(R.drawable.like), //usa imagen importada
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .offset(x = 15.dp, y = 100.dp))

                Image(
                    painter = painterResource(R.drawable.comentario), //usa imagen importada
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .offset(x = 15.dp, y = 100.dp))

                Image(
                    painter = painterResource(R.drawable.send), //usa imagen importada
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .offset(x = 10.dp, y = 100.dp))

                Image(
                    painter = painterResource(R.drawable.guardar), //usa imagen importada
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .offset(x = 135.dp, y = 100.dp))

            }

            Row(modifier = Modifier.offset(x = 40.dp, y = 157.dp)){
                Image(painter = painterResource(R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(x = 20.dp, y = -5.dp))

                Text(text = stringResource(R.string.Numero_likes),
                    modifier = Modifier.offset(x = 22.dp, y = -5.dp),
                    fontSize = 15.sp)
            }

            Text(text = stringResource(R.string.Descripcion),
                fontSize = 15.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 0.dp, y = 160.dp)
                    .padding(50.dp, 0.dp))
    }

    }

}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun PerfilPreview() {
    val navController = rememberNavController()

    Proyecto_plataformasMovilesTheme {
        Perfil(innerPadding= PaddingValues())
        CenterAlignedTopAppBar_Perfil(navController = navController)
        BottomAppBarPerfil(innerPadding = PaddingValues())
    }
}
