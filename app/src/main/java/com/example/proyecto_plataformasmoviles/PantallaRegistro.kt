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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.MatchesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import com.example.proyecto_plataformasmoviles.viewmodel.AuthViewModel
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModel
import com.example.proyecto_plataformasmoviles.viewmodel.PerfilViewModelFactory


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
    val dogUbicacion = remember { mutableStateOf("") }
    val dogTamaño = remember { mutableStateOf("") }
    val dogPareja = remember { mutableStateOf("") }
    val dogCria = remember { mutableStateOf("") }
    val dogPedigree = remember { mutableStateOf("") }
    val dogSexo = remember { mutableStateOf("") }
    val dogEntrenamiento = remember { mutableStateOf("") }

    val perfilesRepository = PerfilesRepository()
    val likesRepository = LikesRepository()
    val matchesRepository = MatchesRepository()
    val factory = PerfilViewModelFactory(perfilesRepository, likesRepository, matchesRepository)
    val viewModel: PerfilViewModel = viewModel(factory = factory)

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
                        dogInfo = dogUbicacion,
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
                                ),
                                dogUbicacion
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
                        dogInfo = dogTamaño,
                        menu = {
                            CustomDropdownMenu(
                                listOf(
                                    "Tamaño",
                                    "Pequeño",
                                    "Mediano",
                                    "Grande"
                                ),
                                dogTamaño
                            )
                        }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.partner1),
                        text = stringResource(R.string.Pareja),
                        dogInfo = dogPareja,
                        menu = { CustomDropdownMenu(listOf("Ha tenido pareja?", "Sí", "No"), dogPareja) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.cria1),
                        text = stringResource(R.string.Cria),
                        dogInfo = dogCria,
                        menu = { CustomDropdownMenu(listOf("Desea crías?", "Sí", "No"), dogCria) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.pedigree1),
                        text = stringResource(R.string.Pedigree),
                        dogInfo = dogPedigree,
                        menu = { CustomDropdownMenu(listOf("Tiene pedigree?", "Sí", "No"), dogPedigree) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.sex1),
                        text = stringResource(R.string.Sexo),
                        dogInfo = dogSexo,
                        menu = { CustomDropdownMenu(listOf("Sexo", "Hembra", "Macho"), dogSexo) }
                    )
                }
                item {
                    Campos(
                        image = painterResource(R.drawable.training1),
                        text = stringResource(R.string.Entrenamiento),
                        dogInfo = dogEntrenamiento,
                        menu = { CustomDropdownMenu(listOf("Está entrenado?", "Sí", "No"), dogEntrenamiento) }
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
                            dogUbicacion.value.isNotBlank() &&
                            dogTamaño.value.isNotBlank() &&
                            dogPareja.value.isNotBlank() &&
                            dogCria.value.isNotBlank() &&
                            dogPedigree.value.isNotBlank() &&
                            dogSexo.value.isNotBlank() &&
                            dogEntrenamiento.value.isNotBlank() &&
                            listOf(
                                "Ubicacion",
                                "Tamaño",
                                "Ha tenido pareja?",
                                "Desea crías?",
                                "Tiene pedigree?",
                                "Sexo",
                                "Está entrenado?"
                            ).none { defaultOption ->
                                defaultOption == dogUbicacion.value ||
                                        defaultOption == dogTamaño.value ||
                                        defaultOption == dogPareja.value ||
                                        defaultOption == dogCria.value ||
                                        defaultOption == dogPedigree.value ||
                                        defaultOption == dogSexo.value ||
                                        defaultOption == dogEntrenamiento.value
                            }

                    if (allFieldsFilled) {
                        authViewModel.registrarUsuario(
                            email.value,
                            password.value,
                            onSuccess = { userId ->
                                // Crear el perfil con los datos proporcionados
                                val perfil = Perfil(
                                    antecedentes = dogAntecedentes.value,
                                    cria = dogCria.value == "Sí",
                                    edad_del_perro = dogAge.value.toIntOrNull() ?: 0,
                                    entrenamiento = dogEntrenamiento.value == "Sí",
                                    nombre_del_perro = dogName.value,
                                    pareja = dogPareja.value == "Sí",
                                    pedigree = dogPedigree.value == "Sí",
                                    peso = dogWeight.value.toIntOrNull() ?: 0,
                                    raza_del_perro = dogBreed.value,
                                    sexo = dogSexo.value,
                                    tamaño = dogTamaño.value,
                                    ubicacion = dogUbicacion.value,
                                    usuario_id = userId,
                                    imagen = "" // Placeholder para imagen
                                )

                                // Llamar al método de repositorio para guardar el perfil
                                viewModel.crearPerfil(perfil,
                                    onSuccess = {
                                        Toast.makeText(context, "Perfil creado exitosamente.", Toast.LENGTH_SHORT).show()
                                        navController.navigate("Recomendaciones")
                                    },
                                    onError = { error ->
                                        Toast.makeText(context, "Error al crear perfil: $error", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            },
                            onFailure = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
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

@Composable
fun CustomDropdownMenu(
    list: List<String>,
    selectedValue: MutableState<String> // Estado externo para capturar la selección
) {
    var isExpanded by remember { mutableStateOf(false) }

    // Contenedor para el menú desplegable
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Caja seleccionable para abrir el menú
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = true }
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            // Texto seleccionado o texto predeterminado
            Text(
                text = selectedValue.value.ifEmpty { "Selecciona una opción" },
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Menú desplegable
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .widthIn(max = 250.dp)
        ) {
            list.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedValue.value = item // Selección del texto
                        isExpanded = false // Cierra el menú
                    },
                    text = { // Aquí usamos el parámetro text directamente
                        Text(
                            text = item,
                            color = Color.Black
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Campos(
    image: Painter,
    text: String,
    dogInfo: MutableState<String>? = null, // Puede ser nulo
    onValueChange: ((String) -> Unit)? = null,
    menu: @Composable ((MutableState<String>) -> Unit)? = null // Menú opcional
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Título encima del campo
        Text(
            text = text,
            color = Color(0xFF54398c),
            fontSize = 15.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Campo de entrada o menú desplegable
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF78A2AB)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Imagen a la izquierda
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )

                // Menú desplegable o campo de texto
                dogInfo?.let { nonNullDogInfo ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        if (menu != null) {
                            menu(nonNullDogInfo) // Renderizar el menú con el estado de dogInfo
                        } else {
                            BasicTextField(
                                value = nonNullDogInfo.value,
                                onValueChange = { newValue ->
                                    nonNullDogInfo.value = newValue // Actualiza el estado
                                    onValueChange?.invoke(newValue) // Callback opcional
                                },
                                textStyle = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Default
                                ),
                                decorationBox = { innerTextField ->
                                    Box(
                                        Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (nonNullDogInfo.value.isEmpty()) {
                                            Text(
                                                text = "Escribe aquí...",
                                                color = Color.LightGray,
                                                fontSize = 16.sp,
                                                fontFamily = cocoFontFamily
                                            )
                                        }
                                        innerTextField() // Campo de texto
                                    }
                                }
                            )
                        }
                    }
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
