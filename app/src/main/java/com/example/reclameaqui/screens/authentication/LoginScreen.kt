package com.example.reclameaqui.screens.authentication

import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reclameaqui.ui.theme.AzulBackground
import com.example.reclameaqui.R
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.CinzaFracoTextField
import com.example.reclameaqui.ui.theme.bodyFontFamily
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AzulBackground)) {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
            .fillMaxSize()) {

            // Imagem familia feliz.
            Image(painter = painterResource(R.drawable.familia_loginscreen1),
                contentDescription = stringResource(R.string.desenho_ilustrativo_de_uma_familia_feliz_loginscreen)
            )

            // Texto "Reclame aqui".
            Text(text = stringResource(R.string.reclame_aqui_loginscreen),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = displayFontFamily,
                color = AzulForteText,
                modifier = Modifier
                    .padding(bottom = 24.dp))

            // TextField E-mail.
            TextField(value = "",
                onValueChange = { },
                placeholder = { Text(
                    text = stringResource(R.string.e_mail_loginscreen),
                    color = CinzaFracoTextField,
                    fontFamily = bodyFontFamily
                ) },
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            // TextField Senha.
            TextField(value = "",
                onValueChange = { },
                placeholder = { Text(
                    text = stringResource(R.string.senha_loginscreen),
                    color = CinzaFracoTextField,
                    fontFamily = bodyFontFamily
                ) },
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            // Botão "Entrar"
            Button(
                // enabled = 
                onClick = { /*Função de Login*/ },
                content = { Text(text = stringResource(R.string.entrar_loginscreen),
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Text "Primeira vez aqui?"
                Text(text = stringResource(R.string.primeira_vez_aqui_loginscreen),
                    fontFamily = displayFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = AzulForteText)
                // TextButton "Cadastre-se"
                TextButton(
                    // enabled =
                    onClick = { /* Nav Tela de Cadastro */ },
                    content = { Text(text = stringResource(R.string.cadastre_se_login),
                                fontFamily = displayFontFamily,
                                fontSize = 16.sp,
                                color = AzulForteText,
                                textDecoration = TextDecoration.Underline)}
                )
            }
        }
    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}