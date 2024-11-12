package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em


class PantallaInicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Proyecto_plataformasMovilesTheme {
                InicioSesion(navController = navController, authViewModel = AuthViewModel())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioSesion(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    // Variables
    val logonb = painterResource(R.drawable.logo_nobg)
    val fucsia = Color(0xFFbb4491)
    val morado = Color(0xFF54398c)
    val rosado = Color(0xFFECCCE2)

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val userValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val signInState by authViewModel.signInState.observeAsState()

    // If sign in fails
    if (signInState == false) {
        Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(color = rosado)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.width(50.dp))
            Text(
                text = buildAnnotatedString {
                    append("INICIO DE\n")
                    withStyle(style = SpanStyle(fontSize = 45.sp)) {
                        append("SESIÓN")
                    }
                },
                color = morado,
                fontSize = 45.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .paddingFromBaseline(top = 150.dp)
                    .offset(x = (-25).dp),
                lineHeight = 1.4.em
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = logonb,
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(id = R.string.logonb),
            modifier = Modifier
                .size(145.dp)
                .offset(y = (115).dp)
                .align(Alignment.End)
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            // User Name TextField
            TextField(
                value = userValue.value,
                onValueChange = { userValue.value = it },
                label = { Text(stringResource(id = R.string.user_name)) },
                maxLines = 1,
                textStyle = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = cocoFontFamily,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .width(350.dp),
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = stringResource(id = R.string.user),
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.White,
                    focusedIndicatorColor = fucsia,
                    cursorColor = fucsia,
                    focusedLeadingIconColor = fucsia,
                    unfocusedIndicatorColor = fucsia.copy(.5f),
                    focusedTrailingIconColor = fucsia,
                    unfocusedTrailingIconColor = fucsia,
                    containerColor = fucsia
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            Text(
                text = "Ingrese su nombre de usuario",
                fontSize = 18.sp,
                color = morado,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 100.dp)
            )

            // Contraseña
            TextField(
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                label = { Text("Contraseña") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus() // Cierra el teclado al terminar
                    }
                ),
                textStyle = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = cocoFontFamily,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .width(350.dp)
                    .paddingFromBaseline(top = 150.dp),
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Lock,
                        contentDescription = stringResource(id = R.string.user),
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = Color.White,
                    focusedIndicatorColor = fucsia,
                    cursorColor = fucsia,
                    focusedLeadingIconColor = fucsia,
                    unfocusedIndicatorColor = fucsia.copy(.5f),
                    focusedTrailingIconColor = fucsia,
                    unfocusedTrailingIconColor = fucsia,
                    containerColor = fucsia
                )
            )

            Text(
                text = "Ingrese su contraseña",
                fontSize = 18.sp,
                color = morado,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 230.dp)
            )

            // FilterChip para mostrar/ocultar contraseña
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 260.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                var selected by remember { mutableStateOf(false) }

                FilterChip(
                    onClick = {
                        selected = !selected
                        passwordVisibility = selected
                    },
                    label = {
                        Text("")
                    },
                    selected = selected,
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else {
                        null
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = fucsia,
                        labelColor = Color.White,
                        selectedContainerColor = rosado,
                        selectedLabelColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 20.dp, height = 20.dp)
                )

                Text(
                    text = "Mostrar contraseña",
                    fontSize = 16.sp,
                    color = morado,
                    fontFamily = cocoFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .paddingFromBaseline(top = 20.dp)
                        .offset(x = 2.dp),
                )
                ClickableText(
                    text = buildAnnotatedString {
                        append("Regístrate Aquí")
                    },
                    onClick = {
                        navController.navigate("Registro")
                    },
                    modifier = Modifier
                        .paddingFromBaseline(top = 19.dp)
                        .offset(x = 50.dp),
                    style = TextStyle(
                        color = morado,
                        fontWeight = FontWeight.Bold,
                        fontFamily = cocoFontFamily,
                        fontSize = 16.sp
                    )
                )
            }
            // Botón para iniciar sesión
            FilledTonalButton(
                onClick = {
                    authViewModel.signInWithEmailAndPassword(
                        email = userValue.value,
                        password = passwordValue.value
                    ) { success, message ->
                        if (success) {
                            navController.navigate("Perfil")
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .padding(25.dp)
                    .height(60.dp)
                    .offset(y = 300.dp)
                    .fillMaxWidth()
                    .background(fucsia, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("Iniciar Sesión")
            }
        }
    }
}
