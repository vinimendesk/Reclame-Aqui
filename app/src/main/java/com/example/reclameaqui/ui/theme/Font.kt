package com.example.reclameaqui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.reclameaqui.R

@Composable
fun poppinsFontFamily() = FontFamily(
    // Título
    Font(R.font.poppins_black, weight = FontWeight.Black),
    // Texto
    Font(R.font.poppins_light, weight = FontWeight.Light)
)