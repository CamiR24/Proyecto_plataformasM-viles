package com.example.proyecto_plataformasmoviles.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.proyecto_plataformasmoviles.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val cocoFontFamily = FontFamily(Font(R.font.cocogothicfattrial, FontWeight.ExtraBold),
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