package com.example.proyecto_plataformasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_plataformasmoviles.ui.theme.cocoFontFamily
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

class Chat : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatScreen()
        }
    }
}

@Composable
fun ChatScreen() {
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = "Leah",
            color = Color(0xFFbb4491),
            fontSize = 32.sp,
            fontFamily = cocoFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, bottom = 24.dp)
                .offset(y = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ChatBubble(
            text = "Hola soy la dueña de Leah, ¿donde nos podríamos juntar hoy? \uD83D\uDE0C",
            color = Color(0xFFbb4491),
            alignment = Alignment.Start,
            imageRes = R.drawable.dog1
        )

        ChatBubble(
            text = "Hola, ¿te parece en el parque a las 2 de la tarde? Toby y yo estaremos ahí 🐶",
            color = Color(0xFF4A90E2),
            alignment = Alignment.End,
            imageRes = R.drawable.dog2
        )

        ChatBubble(
            text = "Está bien, aquí te esperamos con Molly 🐕",
            color = Color(0xFFbb4491),
            alignment = Alignment.Start,
            imageRes = R.drawable.dog1
        )

        ChatBubble(
            text = "Super te miro pronto!!! ☺",
            color = Color(0xFF4A90E2),
            alignment = Alignment.End,
            imageRes = R.drawable.dog2
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .imePadding()
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                placeholder = { Text(text = "Escribe un mensaje...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        keyboardController?.hide()
                    }
                )
            )
            IconButton(onClick = {
                keyboardController?.hide()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = "Enviar mensaje",
                    tint = Color(0xFF4A90E2)
                )
            }
        }
    }
}

@Composable
fun ChatBubble(text: String, color: Color, alignment: Alignment.Horizontal, imageRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (alignment == Alignment.Start) Arrangement.Start else Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        if (alignment == Alignment.Start) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFb8a0e8))
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = color,
            modifier = Modifier
                .widthIn(0.dp, 250.dp)
                .padding(vertical = 10.dp)
                .offset(x = -5.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp),
                textAlign = if (alignment == Alignment.End) TextAlign.End else TextAlign.Start
            )
        }

        if (alignment == Alignment.End) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF78A2AB))
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen()
}
