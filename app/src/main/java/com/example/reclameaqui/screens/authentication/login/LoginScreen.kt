package com.example.reclameaqui.screens.authentication.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.reclameaqui.ui.theme.AzulBackground
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.navigation.MainNavigation
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.poppinsFontFamily

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier
) {

    val loginViewModel: LoginViewModel = viewModel()
    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val authState = authViewModel.authState.collectAsState()
    val context = LocalContext.current

    // Variáveis TextFields.
    val emailError = loginUiState.emailError
    val emailShake = shakeAnimation(emailError, null)
    val emailColor = errorContainerColor(emailError, null)
    val emailText = errorTextColor(emailError, null)
    val email = loginUiState.email
    val passwordVisibility = loginUiState.passwordVisible

    val passwordError = loginUiState.passwordError
    val passwordShake = shakeAnimation(passwordError, null)
    val passwordColor = errorContainerColor(passwordError, null)
    val passwordText = errorTextColor(passwordError, null)
    val password = loginUiState.password

    /*LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> MainNavigation(authViewModel, modifier)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }*/

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
                fontFamily = poppinsFontFamily(),
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
                    fontFamily = poppinsFontFamily()
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
            // val keyboardController = LocalSoftwareKeyboardController.current

            TextField(value = password,
                onValueChange = { password ->
                    loginViewModel.onPasswordChange(password)
                                },
                placeholder = { Text(
                    text = stringResource(R.string.senha_loginscreen),
                    color = passwordText,
                    fontFamily = poppinsFontFamily()
                ) },
                singleLine = true,
                // isError = passwordError, Define o estado de erro do TextField
                shape = RoundedCornerShape(25.dp),
                /*keyboardActions = KeyboardActions(
                    onDone = {

                        loginViewModel.showValidationErrors(context)

                        if (loginUiState.isValid) {
                            navController.navigate(ScreenType.RECENTCOMPLAINTS.name)
                        }

                    }
                ),*/
                visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    val image = if (passwordVisibility) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                    IconButton(
                        onClick = { loginViewModel.togglePasswordVisbility() }
                    ) {
                        Icon(
                            image,
                            contentDescription = if (passwordVisibility) {
                                stringResource(R.string.n_o_mostrar_senha_passwordTextField)
                            } else {
                                stringResource(R.string.mostrar_senha_passwordTextField)
                            }
                        )
                    }
                },
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
                enabled = authState.value != AuthState.Loading,
                onClick = {

                    loginViewModel.showValidationErrors(context)

                    if (loginUiState.isValid) {
                        authViewModel.login(
                            loginUiState.email,
                            loginUiState.password,
                            context)
                    }

                },
                content = { Text(text = stringResource(R.string.entrar_loginscreen),
                    fontFamily = poppinsFontFamily(),
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
                    fontFamily = poppinsFontFamily(),
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = AzulForteText)
                // TextButton "Cadastre-se"
                TextButton(
                    enabled = authState != AuthState.Loading,
                    onClick = { navController.navigate(ScreenType.SINGUP.name) },
                    content = { Text(text = stringResource(R.string.cadastre_se_login),
                                fontFamily = poppinsFontFamily(),
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
    val authViewModel: AuthViewModel = viewModel()
    val navController = rememberNavController()
    LoginScreen(authViewModel, navController, Modifier)
}