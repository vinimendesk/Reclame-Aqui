package com.example.reclameaqui.screens.authentication.singup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reclameaqui.R
import com.example.reclameaqui.screens.authentication.singup.components.ProfileImagePicker
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun SingUpScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(RosaBackground)) {
        
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Botão de retornar a página.
            IconButton(
                onClick = { /* Navegar para LoginScreen */ },
                modifier = Modifier
                    .padding(start = 10.dp, top = 8.dp, bottom = 60.dp)
                    .size(40.dp)

            )  {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.retornar_a_tela_de_login_singupscreen),
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            // Text "Cadastro"
            Text(text = stringResource(R.string.cadastro_singUp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = displayFontFamily,
                color = AzulForteText,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 60.dp))
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .fillMaxSize()) {

            // Botão de escolha da imagem de perfil.
            ProfileImagePicker(modifier = Modifier.padding(bottom = 16.dp))

        }

    }

}

@Preview
@Composable
fun SingUpScreenPreview() {
    SingUpScreen()
}