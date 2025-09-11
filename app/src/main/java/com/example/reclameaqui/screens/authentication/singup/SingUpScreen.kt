package com.example.reclameaqui.screens.authentication.singup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.screens.authentication.singup.components.ProfileImagePicker
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.CinzaFracoTextField
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.bodyFontFamily
import com.example.reclameaqui.ui.theme.displayFontFamily
import com.example.reclameaqui.ui.theme.poppinsFontFamily

@Composable
fun SingUpScreen(
    singUpViewModel: SingUpViewModel,
    navController: NavController,
    modifier: Modifier
) {

    val singUpUiState by singUpViewModel.singUpUiState.collectAsState()

    // Shake animation nos TextField vazios.
    val nameColor = shakeAnimation(singUpUiState.nameError, null)
    val emailColor = shakeAnimation(singUpUiState.emailError, null)

    Box(modifier = modifier
        .fillMaxSize()
        .background(RosaBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 35.dp)
            ) {
                // Botão de retornar a página.
                IconButton(
                    onClick = { navController.navigate(ScreenType.LOGIN.name) },
                    modifier = Modifier
                        .systemBarsPadding()
                        .padding(start = 10.dp, top = 8.dp, bottom = 20.dp)
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
                    fontFamily = poppinsFontFamily(),
                    color = AzulForteText,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Botão de escolha da imagem de perfil.
                ProfileImagePicker(modifier = Modifier.padding(bottom = 35.dp))

                // SEÇÃO TEXTFIELDS
                // TextField Nome.
                TextField(
                    value = singUpUiState.name,
                    onValueChange = { name ->
                        singUpViewModel.onNameChange(name)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.seu_nome_singup),
                            color = errorTextColor(singUpUiState.nameError, null),
                            fontFamily = poppinsFontFamily()
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = errorContainerColor(singUpUiState.nameError, null),
                        unfocusedContainerColor = errorContainerColor(singUpUiState.nameError, null),
                        disabledContainerColor = errorContainerColor(singUpUiState.nameError, null),
                        errorContainerColor = errorContainerColor(singUpUiState.nameError, null)
                    ),
                    modifier = Modifier
                        .graphicsLayer { translationX = nameColor }
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField E-mail
                TextField(
                    value = singUpUiState.email,
                    onValueChange = { email ->
                        singUpViewModel.onEmailChange(email)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.seu_email_singup),
                            color = errorTextColor(singUpUiState.emailError, null),
                            fontFamily = poppinsFontFamily()
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = errorContainerColor(singUpUiState.emailError, null),
                        unfocusedContainerColor = errorContainerColor(singUpUiState.emailError, null),
                        disabledContainerColor = errorContainerColor(singUpUiState.emailError, null),
                        errorContainerColor = errorContainerColor(singUpUiState.emailError, null)
                    ),
                    modifier = Modifier
                        .graphicsLayer { translationX = emailColor }
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField O que mais te agrada?.
                TextField(
                    value = singUpUiState.whatMoreLike,
                    onValueChange = { whatMoreLike ->
                        singUpViewModel.onWhatMoreLikeChange(whatMoreLike) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.o_que_mais_te_agrada_singup),
                            color = CinzaFracoTextField,
                            fontFamily = poppinsFontFamily()
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
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
                    value = singUpUiState.whatMoreDislike,
                    onValueChange = { whatMoreDislike ->
                        singUpViewModel.onWhatMoreDislikeChange(whatMoreDislike) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.o_que_mais_te_irrita_singup),
                            color = CinzaFracoTextField,
                            fontFamily = poppinsFontFamily()
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
            onClick = {

                singUpViewModel.showValidationErrors()

                if (singUpUiState.isValidSingUP) {
                    singUpViewModel.showValidationErrosFalse()
                    navController.navigate(ScreenType.SINGUPPASSWORD.name)
                }
                      },
            modifier = Modifier
                .navigationBarsPadding()
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
    /*val navControler = rememberNavController()
    SingUpScreen(navControler, modifier = Modifier)*/
}