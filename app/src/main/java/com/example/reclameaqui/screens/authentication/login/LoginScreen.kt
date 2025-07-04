package com.example.reclameaqui.screens.authentication.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import com.example.reclameaqui.ui.theme.AzulBackground
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.CinzaFracoTextField
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.bodyFontFamily
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier
) {

    val loginViewModel: LoginViewModel = viewModel()
    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val context = LocalContext.current

    // Variáveis TextFields.
    val emailError = loginUiState.emailError
    val emailShake = shakeAnimation(emailError, null)
    val emailColor = errorContainerColor(emailError, null)
    val emailText = errorTextColor(emailError, null)
    val email = loginUiState.email

    val passwordError = loginUiState.passwordError
    val passwordShake = shakeAnimation(passwordError, null)
    val passwordColor = errorContainerColor(passwordError, null)
    val passwordText = errorTextColor(passwordError, null)
    val password = loginUiState.password

    Box(modifier = modifier
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
            TextField(value = email,
                onValueChange = { email ->
                    loginViewModel.onEmailChange(email)
                },
                placeholder = { Text(
                    text = stringResource(R.string.e_mail_loginscreen),
                    color = emailText,
                    fontFamily = bodyFontFamily
                ) },
                singleLine = true,
                // isError = emailError, Define o estado de error do TextField
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = emailColor,
                    unfocusedContainerColor = emailColor,
                    disabledContainerColor = emailColor,
                    errorContainerColor = emailColor
                ),
                modifier = Modifier
                    .graphicsLayer { translationX = emailShake }
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            // TextField Senha.
            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(value = password,
                onValueChange = { password ->
                    loginViewModel.onPasswordChange(password)
                                },
                placeholder = { Text(
                    text = stringResource(R.string.senha_loginscreen),
                    color = passwordText,
                    fontFamily = bodyFontFamily
                ) },
                singleLine = true,
                // isError = passwordError, Define o estado de erro do TextField
                shape = RoundedCornerShape(25.dp),
                keyboardActions = KeyboardActions(
                    onDone = {

                        loginViewModel.showValidationErrors(context)

                        if (loginUiState.isValid) {
                            navController.navigate(ScreenType.RECENTCOMPLAINTS.name)
                        }

                    }
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = passwordColor,
                    unfocusedContainerColor = passwordColor,
                    disabledContainerColor = passwordColor,
                    errorContainerColor = passwordColor
                ),
                modifier = Modifier
                    .graphicsLayer { translationX = passwordShake }
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            // Botão "Entrar"
            Button(
                // enabled = 
                onClick = {

                    loginViewModel.showValidationErrors(context)

                    if (loginUiState.isValid) {
                        navController.navigate(ScreenType.RECENTCOMPLAINTS.name)
                    }

                },
                content = { Text(text = stringResource(R.string.entrar_loginscreen),
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
                },
                colors = ButtonDefaults.buttonColors(containerColor = RoxoButton),
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
                    onClick = { navController.navigate(ScreenType.SINGUP.name) },
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
    val navController = rememberNavController()
    LoginScreen(navController, Modifier)
}