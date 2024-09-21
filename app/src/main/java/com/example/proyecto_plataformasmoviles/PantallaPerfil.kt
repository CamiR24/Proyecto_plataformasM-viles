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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme

class PantallaPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBar_Perfil() {
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
        Perfil(innerPadding)
    }
}

@Composable
fun Perfil(innerPadding: PaddingValues) {
    Surface(color = Color(0xFFFFE1F3)){
        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.offset(x = 40.dp, y = 80.dp)){
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .size(35.dp)
                )

                Text(text = stringResource(R.string.Nombre),
                    modifier = Modifier.offset(x = 10.dp, y = 5.dp),
                    fontSize = 23.sp)
            }

            Image(
                painter = painterResource(R.drawable.leah), //usa imagen importada
                contentDescription = null,
                modifier = Modifier
                    .size(380.dp)
                    .wrapContentSize()
                    .offset(x = 4.dp, y = 100.dp)
            )

            Row(modifier = Modifier.offset(x = 40.dp, y = 15.dp)){
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

            Row(modifier = Modifier.offset(x = 40.dp, y = 117.dp)){
                Image(painter = painterResource(R.drawable.like),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(x = 20.dp, y = -5.dp))

                Text(text = stringResource(R.string.Numero_likes),
                    modifier = Modifier.offset(x = 22.dp, y = -5.dp),
                    fontSize = 15.sp)
            }

            Text(text = stringResource(R.string.Descripción),
                fontSize = 15.sp,
                modifier = Modifier
                    .offset(x = 13.dp, y = 130.dp)
                    .padding(50.dp, 0.dp))
    }

    }

}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun PerfilPreview() {
    Proyecto_plataformasMovilesTheme {
        Perfil(innerPadding= PaddingValues())
        CenterAlignedTopAppBar_Perfil()
    }
}
