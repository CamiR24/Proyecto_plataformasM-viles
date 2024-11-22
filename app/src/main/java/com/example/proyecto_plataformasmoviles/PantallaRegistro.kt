package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import com.example.proyecto_plataformasmoviles.viewmodel.AuthViewModel


class PantallaRegistro : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        auth = FirebaseAuth.getInstance()
        auth.useAppLanguage()

        setContent {
            Proyecto_plataformasMovilesTheme {

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
                    .offset(x = 20.dp, y = 10.dp)
                    .padding(horizontal = 50.dp, vertical = 30.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Campos(
                        image = painterResource(R.drawable.email1),
                        text = stringResource(R.string.Email),
                        dogInfo = email,
                        onValueChange = { email.value = it }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.password1),
                        text = stringResource(R.string.Contraseña),
                        dogInfo = password,
                        onValueChange = { password.value = it }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.perro),
                        text = stringResource(R.string.Nombre_del_perro),
                        dogInfo = dogName
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.huellas),
                        text = stringResource(R.string.Raza_del_perro),
                        dogInfo = dogBreed
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.ubicacion),
                        text = stringResource(R.string.Ubicacion),
                        menu = {
                            CustomDropdownMenu(
                                listOf(
                                    "Ubicacion",
                                    "Guatemala",
                                    "Baja Verapaz",
                                    "Alta Verapaz",
                                    "El Progreso",
                                    "Izabal",
                                    "Chiquimula",
                                    "Zacapa",
                                    "Santa Rosa",
                                    "Jalapa",
                                    "Jutiapa",
                                    "Sacatepéquez",
                                    "Chimaltenango",
                                    "Escuintla",
                                    "Sololá",
                                    "Totonicapán",
                                    "Quetzaltenango",
                                    "Suchitepéquez",
                                    "Retalhuleu",
                                    "San Marcos",
                                    "Huehuetenango",
                                    "Quiché",
                                    "Petén"
                                )
                            )
                        }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.reloj),
                        text = stringResource(R.string.Edad_del_perro),
                        dogInfo = dogAge
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.weight1),
                        text = stringResource(R.string.Peso),
                        dogInfo = dogWeight
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.size1),
                        text = stringResource(R.string.Tamaño),
                        menu = {
                            CustomDropdownMenu(
                                listOf(
                                    "Tamaño",
                                    "Pequeño",
                                    "Mediano",
                                    "Grande"
                                )
                            )
                        }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.partner1),
                        text = stringResource(R.string.Pareja),
                        menu = { CustomDropdownMenu(listOf("Ha tenido pareja?", "Sí", "No")) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.cria1),
                        text = stringResource(R.string.Cria),
                        menu = { CustomDropdownMenu(listOf("Desea crías?", "Sí", "No")) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.pedigree1),
                        text = stringResource(R.string.Pedigree),
                        menu = { CustomDropdownMenu(listOf("Tiene pedigree?", "Sí", "No")) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.sex1),
                        text = stringResource(R.string.Sexo),
                        menu = { CustomDropdownMenu(listOf("Sexo", "Hembra", "Macho")) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.training1),
                        text = stringResource(R.string.Entrenamiento),
                        menu = { CustomDropdownMenu(listOf("Está entrenado?", "Sí", "No")) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.antecedentes1),
                        text = stringResource(R.string.Antecedentes),
                        dogInfo = dogAntecedentes
                    )
                }
            }

            Button(
                onClick = {
                    val allFieldsFilled = email.value.isNotBlank() &&
                            password.value.isNotBlank() &&
                            dogName.value.isNotBlank() &&
                            dogBreed.value.isNotBlank() &&
                            dogAge.value.isNotBlank() &&
                            dogWeight.value.isNotBlank() &&
                            dogAntecedentes.value.isNotBlank() &&
                            listOf(
                                "Ubicacion",
                                "Tamaño",
                                "Ha tenido pareja?",
                                "Desea crías?",
                                "Tiene pedigree?",
                                "Sexo",
                                "Está entrenado?"
                            )
                                .none { defaultOption -> defaultOption == dogBreed.value || defaultOption == dogAge.value }
                    if (allFieldsFilled) {
                        authViewModel.registrarUsuario(
                            email.value,
                            password.value,
                            navController
                        )
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor completa todos los campos y selecciona una opción válida.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFbb4491),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.Next),
                    fontSize = 20.sp,
                    fontFamily = cocoFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(list: List<String>) {
    val list = list
    var isExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = selectedText,
            modifier = Modifier
                .padding()
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
    dogInfo: MutableState<String>? = null,
    onValueChange: ((String) -> Unit)? = null,
    menu: @Composable (() -> Unit)? = null,
){
    Text(text = text,
        color = Color(0xFF54398c),
        fontSize = 15.sp,
        fontFamily = cocoFontFamily,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .offset(x = 15.dp, y = 0.dp)
            .padding(50.dp, 0.dp))

    Card(colors = CardColors(Color(0xFF78A2AB), Color(0xFFFFFFFF), Color(0xFF78A2AB), Color(0xFF78A2AB)),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Image(painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp))

            dogInfo?.let{ nonNullDogInfo ->
                TextField(
                    value = nonNullDogInfo.value,
                    colors = get_color(),
                    onValueChange = { value ->
                        nonNullDogInfo.value = value
                        onValueChange?.invoke(value)
                    },
                    label = { Text(text = text) },
                    modifier = Modifier.weight(1f)
                )
            }

            if (menu != null) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 8.dp)
                ) {
                    menu()
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
        Registro(innerPadding = PaddingValues(16.dp), navController = navController, authViewModel = AuthViewModel())
    }
}
