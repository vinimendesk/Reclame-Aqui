package com.example.reclameaqui.screens.authentication.singup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.R
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.screens.authentication.singup.components.ProfileImagePicker
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.CinzaFracoTextField
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.bodyFontFamily
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun SingUpScreen(
    navController: NavController,
    modifier: Modifier
) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(RosaBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 90.dp)
            ) {
                // Botão de retornar a página.
                IconButton(
                    onClick = { navController.navigate(ScreenType.LOGIN.name) },
                    modifier = Modifier
                        .padding(start = 10.dp, top = 8.dp, bottom = 60.dp)
                        .size(40.dp)

                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.retornar_a_tela_de_login_singupscreen),
                        modifier = Modifier
                            .size(40.dp)
                    )
                }

                // Text "Cadastro"
                Text(
                    text = stringResource(R.string.cadastro_singUp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = displayFontFamily,
                    color = AzulForteText,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Botão de escolha da imagem de perfil.
                ProfileImagePicker(modifier = Modifier.padding(bottom = 90.dp))

                // SEÇÃO TEXTFIELDS
                // TextField Nome.
                TextField(
                    value = "",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.seu_nome_singup),
                            color = CinzaFracoTextField,
                            fontFamily = bodyFontFamily
                        )
                    },
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
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField E-mail
                TextField(
                    value = "",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.seu_email_singup),
                            color = CinzaFracoTextField,
                            fontFamily = bodyFontFamily
                        )
                    },
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
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField O que mais te agrada?.
                TextField(
                    value = "",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.o_que_mais_te_agrada_singup),
                            color = CinzaFracoTextField,
                            fontFamily = bodyFontFamily
                        )
                    },
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
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField O que mais te irrita?
                TextField(
                    value = "",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.o_que_mais_te_irrita_singup),
                            color = CinzaFracoTextField,
                            fontFamily = bodyFontFamily
                        )
                    },
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
                        .padding(start = 24.dp, end = 24.dp)
                )

            }
        }

    }

    Box(contentAlignment = Alignment.BottomEnd,
        modifier = modifier
        .fillMaxSize()
    ) {
        // Botão para ir á página de cadastro de senha.
        IconButton(
            onClick = { navController.navigate(ScreenType.SINGUPPASSWORD.name) },
            modifier = Modifier
                .padding(end = 10.dp, bottom = 16.dp)
                .size(40.dp)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = stringResource(R.string.retornar_a_tela_de_login_singupscreen),
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }


}

@Preview
@Composable
fun SingUpScreenPreview() {
    val navControler = rememberNavController()
    SingUpScreen(navControler, modifier = Modifier)
}