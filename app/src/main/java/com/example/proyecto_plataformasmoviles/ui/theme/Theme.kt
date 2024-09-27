package com.example.proyecto_plataformasmoviles.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.proyecto_plataformasmoviles.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

val cocoFontFamily = FontFamily(
    Font(R.font.cocogothicfattrial, FontWeight.ExtraBold),
    Font(R.font.cocogothicboldtrial, FontWeight.Bold),
    Font(R.font.cocogothicultralighttrial, FontWeight.ExtraLight),
    Font(R.font.cocogothicultralightitalictrial, FontWeight.ExtraLight),
    Font(R.font.cocogothiclighttrial, FontWeight.Light),
    Font(R.font.cocogothiclightitalictrial, FontWeight.Light),
    Font(R.font.cocogothicitalictrial, FontWeight.Normal),
    Font(R.font.cocogothicheavytrial, FontWeight.Bold),
    Font(R.font.cocogothicheavyitalictrial, FontWeight.Bold),
    Font(R.font.cocogothicfatitalictrial, FontWeight.ExtraBold),
    Font(R.font.cocogothicbolditalictrial, FontWeight.Bold),
    Font(R.font.cocogothictrial, FontWeight.Normal)
)

@Composable
fun Proyecto_plataformasMovilesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
