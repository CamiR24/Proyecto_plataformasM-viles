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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.proyecto_plataformasmoviles.AuthViewModel


class PantallaRegistro : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()

        setContent {
            Proyecto_plataformasMovilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Perfil(innerPadding = PaddingValues())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(innerPadding: PaddingValues, navController: NavHostController, authViewModel: AuthViewModel) {

    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val dogName = remember { mutableStateOf("") }
    val dogBreed = remember { mutableStateOf("") }
    val dogAge = remember { mutableStateOf("") }
    val dogWeight = remember { mutableStateOf("") }
    val dogAntecedentes = remember { mutableStateOf("") }

    Surface(color = Color(0xFFECCCE2)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.Registro),
                color = Color(0xFFbb4491),
                fontSize = 50.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(x = 20.dp, y = 90.dp)
                    .padding(50.dp, 0.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Email),
                    dogInfo = email,
                    onValueChange = { email.value = it }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Contraseña),
                    dogInfo = password,
                    onValueChange = { password.value = it }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Nombre_del_perro),
                    dogInfo = dogName
                )
                Campos(
                    image = painterResource(R.drawable.huellas),
                    text = stringResource(R.string.Raza_del_perro),
                    dogInfo = dogBreed
                )
                Campos(
                    image = painterResource(R.drawable.ubicacion),
                    text = stringResource(R.string.Ubicacion),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() })
                Campos(
                    image = painterResource(R.drawable.reloj),
                    text = stringResource(R.string.Edad_del_perro),
                    dogInfo = dogAge
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Peso),
                    dogInfo = dogWeight
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Tamaño),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Pareja),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Cria),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Pedigree),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Sexo),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Entrenamiento),
                    dogInfo = dogName,
                    menu = { CustomDropdownMenu() }
                )
                Campos(
                    image = painterResource(R.drawable.perro),
                    text = stringResource(R.string.Antecedentes),
                    dogInfo = dogAntecedentes
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        // Llamada a la función del ViewModel para registrar al usuario
                        authViewModel.registrarUsuario(
                            email.value,
                            password.value,
                            navController
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFbb4491),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(250.dp, 60.dp)
                ) {
                    Text(
                        text = stringResource(R.string.Next),
                        fontSize = 35.sp,
                        fontFamily = cocoFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
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

@Composable
fun Campos(
    image: Painter,
    text: String,
    dogInfo: MutableState<String>,
    onValueChange: ((String) -> Unit)? = null,
    menu: @Composable (() -> Unit)? = null,
){
    Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
        modifier = Modifier
            .size(350.dp, 60.dp)
            .offset(x = 22.dp, y = 200.dp)) {
        Row(){
            Image(painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = 5.dp, y = 5.dp))

            TextField(
                value = dogInfo.value,
                colors = get_color(),
                onValueChange = { value ->
                    dogInfo.value = value
                    onValueChange?.invoke(value)
                },
                label = { Text(text = text) }
            )

            if (menu != null) {
                menu()
            }
        }
    }

    Text(text = text,
        color = Color(0xFF54398c),
        fontSize = 15.sp,
        fontFamily = cocoFontFamily,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .offset(x = 80.dp, y = 270.dp)
            .padding(50.dp, 0.dp))
}


fun get_color() = TextFieldColors(Color(0xFFFFFFFF), Color(0xFFFFFFFF), Color(0xFFFFFFFF),Color(0xFFFFFFFF),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),
    TextSelectionColors(Color(0xFFFFFFFF),Color(0xFFFFFFFF)),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB), Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB),Color(0xFF78A2AB))

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun RegistroPreview() {
    val navController = rememberNavController()

    Proyecto_plataformasMovilesTheme {
        Registro(innerPadding = PaddingValues(16.dp), navController = navController, authViewModel = AuthViewModel())
    }
}
