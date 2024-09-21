package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.proyecto_plataformasmoviles.ui.theme.Proyecto_plataformasMovilesTheme
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily

class PantallaInicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_plataformasMovilesTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFECCCE2)) // Color de fondo aquí
                ) { innerPadding ->
                    InicioSesion(
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
fun InicioSesion(name: String, modifier: Modifier = Modifier) {
    //Variables
    val logonb = painterResource(R.drawable.logo_nobg)
    val fucsia = Color(0xFFbb4491)
    val morado = Color(0xFF54398c)

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECCCE2)) // Color de fondo aquí
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.width(50.dp))
            Text(
                text = buildAnnotatedString { //Para agregar texto abajo
                    append("INICIO DE\n")
                    withStyle(style = SpanStyle(fontSize = 45.sp)) { // Modifica el tamaño de "Campus Central"
                        append("SESIÓN") //Agregar texto
                    }
                },
                color = morado,
                fontSize = 45.sp,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.paddingFromBaseline(top = 150.dp).offset(x = (-25).dp),
                lineHeight = 1.4.em
            )
        }
    }

    
    Column (
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = logonb,
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(id = R.string.logonb),
            modifier = Modifier
                .size(150.dp)
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
        ){
            var text by remember { mutableStateOf("") }

            //User Name TextField
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(id = R.string.user_name)) },
                maxLines = 2,
                textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontFamily = cocoFontFamily, fontSize = 60.sp),
                modifier = Modifier.padding(10.dp).width(350.dp),

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
                )
            )
            Text(
                text = "Ingrese su nombre de usuario",
                fontSize = 17.sp,
                color = morado,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 120.dp)
            )
             //Contraseña
            var password by rememberSaveable { mutableStateOf("") }

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontFamily = cocoFontFamily, fontSize = 60.sp),
                modifier = Modifier.padding(20.dp).width(350.dp).paddingFromBaseline(top = 150.dp),

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
                fontSize = 17.sp,
                color = morado,
                fontFamily = cocoFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.paddingFromBaseline(top = 250.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {

        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun InicioSesionPreview() {
    Proyecto_plataformasMovilesTheme {
        InicioSesion("Android")
    }
}
