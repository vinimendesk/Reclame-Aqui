package com.example.reclameaqui.screens.authentication.singup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.bodyFontFamily
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun SingUpPassword(
    authViewModel: AuthViewModel,
    singUpViewModel: SingUpViewModel,
    navController: NavController,
    modifier: Modifier) {

    val authState = authViewModel.authState.collectAsState()
    val singUpUiState: SingUpUiState by singUpViewModel.singUpUiState.collectAsState()
    val context = LocalContext.current

    // ShakeAnimation para erros nos TextFields.
    val passwordError = shakeAnimation(singUpUiState.passwordError, null)
    val passwordAgainError = shakeAnimation(singUpUiState.passwordAgainError, singUpUiState.incorrectPasswordAgain)

    /*LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate(ScreenType.RECENTCOMPLAINTS.name) {
                    popUpTo(ScreenType.SINGUPPASSWORD.name) { inclusive = true }
                }
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }*/

    Box(modifier = modifier
        .fillMaxSize()
        .background(RosaBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 160.dp)
            ) {
                // Botão de retornar a página.
                IconButton(
                    onClick = { navController.navigate(ScreenType.SINGUP.name) },
                    modifier = Modifier
                        .padding(start = 10.dp, top = 8.dp, bottom = 60.dp)
                        .size(40.dp)

                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.retorna_a_tela_de_cadastro_singuppassword),
                        modifier = Modifier
                            .size(40.dp)
                    )
                }

                // Text "CADASTRE SUA"
                Text(
                    text = stringResource(R.string.cadastre_sua_singuppassword),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = displayFontFamily,
                    color = AzulForteText,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                // Text "CADASTRE SUA"
                Text(
                    text = stringResource(R.string.senha_singuppassword),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = displayFontFamily,
                    textDecoration = TextDecoration.Underline,
                    color = RoxoButton,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }

            // SEÇÃO TEXTFIELDS
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {

                // TextField Senha.
                TextField(
                    value = singUpUiState.password,
                    onValueChange = { password ->
                        singUpViewModel.onPasswordChange(password)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.insira_sua_senha_singuppassword),
                            color = errorTextColor(singUpUiState.passwordError, null),
                            fontFamily = bodyFontFamily
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
                        focusedContainerColor = errorContainerColor(singUpUiState.passwordError, null),
                        unfocusedContainerColor = errorContainerColor(singUpUiState.passwordError, null),
                        disabledContainerColor = errorContainerColor(singUpUiState.passwordError, null),
                        errorContainerColor = errorContainerColor(singUpUiState.passwordError, null)
                    ),
                    modifier = Modifier
                        .graphicsLayer { translationX = passwordError }
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                // TextField Confirme Sua Senha.
                TextField(
                    value = singUpUiState.passwordAgain,
                    onValueChange = { passwordAgain ->
                        singUpViewModel.onPasswordAgainChange(passwordAgain)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.insira_sua_senha_novamente_singuppassword),
                            color = errorTextColor(singUpUiState.passwordAgainError, null),
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
                        focusedContainerColor = errorContainerColor(singUpUiState.passwordAgainError, singUpUiState.incorrectPasswordAgain),
                        unfocusedContainerColor = errorContainerColor(singUpUiState.passwordAgainError, singUpUiState.incorrectPasswordAgain),
                        disabledContainerColor = errorContainerColor(singUpUiState.passwordAgainError, singUpUiState.incorrectPasswordAgain),
                        errorContainerColor = errorContainerColor(singUpUiState.passwordAgainError, singUpUiState.incorrectPasswordAgain)
                    ),
                    modifier = Modifier
                        .graphicsLayer { translationX = passwordAgainError }
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
                )

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Botão "Realizar Cadastro"
                    Button(
                        enabled = authState != AuthState.Loading,
                        onClick = {
                            singUpViewModel.showValidationErrors()

                            if (singUpUiState.isValidSingUpPassword) {
                                authViewModel.singUp(
                                    singUpUiState.email,
                                    singUpUiState.password,
                                    context,
                                    navController
                                )
                            }
                        },
                        content = { Text(text = stringResource(R.string.realizar_cadastro_singuppassword),
                            fontFamily = bodyFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = RoxoButton),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun SingUpPasswordPreview() {
    /*val navController = rememberNavController()
    SingUpPassword(navController, modifier = Modifier)*/
}