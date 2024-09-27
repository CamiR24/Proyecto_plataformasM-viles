package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily

class PantallaRegistro : ComponentActivity() {
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
fun Registro(innerPadding: PaddingValues, navController: NavHostController) {
    var dogName by remember { mutableStateOf("") }
    var dogBreed by remember { mutableStateOf("") }
    var dogAge by remember { mutableStateOf("") }

    Surface(color = Color(0xFFECCCE2)){
        Column(modifier = Modifier.fillMaxSize()){

            Text(text = stringResource(R.string.Registro),
                color = Color(0xFFbb4491),
                fontSize = 50.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 20.dp, y = 90.dp)
                    .padding(50.dp, 0.dp))
        }

        Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
            modifier = Modifier
                .size(350.dp, 60.dp)
                .offset(x = 22.dp, y = 200.dp)) {
            Row(){
                Image(painter = painterResource(R.drawable.perro),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 5.dp, y = 5.dp))

                TextField(
                    value = dogName, //usuario ingresa el valor
                    colors = get_color(),
                    onValueChange = {dogName = it}, //guardamos el valor cada vez que este cambie
                    label = {Text(text = stringResource(R.string.Nombre_del_perro))}
                )
            }
        }

        Text(text = stringResource(R.string.Nombre_del_perro),
            color = Color(0xFF54398c),
            fontSize = 15.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 80.dp, y = 270.dp)
                .padding(50.dp, 0.dp))

        Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
            modifier = Modifier
                .size(350.dp, 60.dp)
                .offset(x = 22.dp, y = 300.dp)) {
            Row(){
                Image(painter = painterResource(R.drawable.huellas),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 5.dp, y = 5.dp))

                TextField(
                    value = dogBreed, //usuario ingresa el valor
                    colors = get_color(),
                    onValueChange = {dogBreed = it}, //guardamos el valor cada vez que este cambie
                    label = {Text(text = stringResource(R.string.Raza_del_perro))}
                )
            }
        }

        Text(text = stringResource(R.string.Raza_del_perro),
            color = Color(0xFF54398c),
            fontSize = 15.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 90.dp, y = 370.dp)
                .padding(50.dp, 0.dp))

        Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
            modifier = Modifier
                .size(350.dp, 60.dp)
                .offset(x = 22.dp, y = 400.dp)) {
            Row(){
                Image(painter = painterResource(R.drawable.ubicacion),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 5.dp, y = 5.dp))

                CustomDropdownMenu()
                    
                }

            }
        }

        Text(text = stringResource(R.string.Ubicacion),
            color = Color(0xFF54398c),
            fontSize = 15.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 110.dp, y = 470.dp)
                .padding(50.dp, 0.dp))

        Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
            modifier = Modifier
                .size(350.dp, 60.dp)
                .offset(x = 22.dp, y = 500.dp)) {
            Row(){
                Image(painter = painterResource(R.drawable.reloj),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 5.dp, y = 5.dp))

                TextField(
                    value = dogAge, //usuario ingresa el valor
                    colors = get_color(),
                    onValueChange = {dogAge = it}, //guardamos el valor cada vez que este cambie
                    label = {Text(text = stringResource(R.string.Edad_del_perro))}
                )
            }
        }

        Text(text = stringResource(R.string.Edad_del_perro),
            color = Color(0xFF54398c),
            fontSize = 15.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 95.dp, y = 570.dp)
                .padding(50.dp, 0.dp))

        Button(onClick = { navController.navigate("Perfil") },
            colors = ButtonColors(Color(0xFFbb4491), Color(0xFFFFFFFF), Color(0xFFbb4491), Color(0xFFbb4491)),
            modifier = Modifier
                .offset(x = 75.dp, 630.dp)
                .size(250.dp, 60.dp)) {
            Text(text = stringResource(R.string.Next),
                fontSize = 35.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold)
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu() {
    val list = listOf("Guatemala" , "Baja Verapaz" , "Alta Verapaz" , "El Progreso" , "Izabal" , "Chiquimula" , "Zacapa" , "Santa Rosa" , "Jalapa" , "Jutiapa" , "Sacatepéquez" , "Chimaltenango" , "Escuintla" , "Sololá" , "Totonicapán" , "Quetzaltenango" , "Suchitepéquez" , "Retalhuleu" , "San Marcos" , "Huehuetenango" , "Quiché" , "Petén")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = selectedText,
            modifier = Modifier
                .padding(16.dp)
                .clickable { isExpanded = true }
                .background(Color.White) // Color del contenedor del texto
        )

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded =! isExpanded}) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .fillMaxHeight(),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},

                colors = ExposedDropdownMenuDefaults.textFieldColors(Color(0xFF78A2AB))
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(Color.Transparent)) {
                list.forEachIndexed{ index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = { selectedText = list[index]
                            isExpanded = false},
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}


fun get_color() = TextFieldColors(Color(0xFFFFFFFF), Color(0xFFFFFFFF), Color(0xFFFFFFFF),Color(0xFFFFFFFF),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),
    TextSelectionColors(Color(0xFFFFFFFF),Color(0xFFFFFFFF)),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB), Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB))

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun RegistroPreview() {
    val navController = rememberNavController()

    Proyecto_plataformasMovilesTheme {
        Registro(innerPadding= PaddingValues(), navController = navController)
    }
}