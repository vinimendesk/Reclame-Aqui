package com.example.reclameaqui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.screens.authentication.login.LoginScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpPassword
import com.example.reclameaqui.screens.authentication.singup.SingUpScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpViewModel
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.RecentComplaintsScreen

@Composable
fun AppNavigation(
    modifier: Modifier
) {

    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val singUpViewModel: SingUpViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = ScreenType.LOGIN.name
    ) {

        // Tela de Login.
        composable(ScreenType.LOGIN.name) {
            LoginScreen(authViewModel, navController, modifier)
        }

        // ----- SEÇÃO CADASTRO -----
        // Tela cadastro.
        composable(ScreenType.SINGUP.name) {
            SingUpScreen(singUpViewModel, navController, modifier)
        }

        // Teça cadastro de senha.
        composable(ScreenType.SINGUPPASSWORD.name) {
            SingUpPassword(authViewModel, singUpViewModel, navController, modifier)
        }

        composable(ScreenType.RECENTCOMPLAINTS.name) {
            RecentComplaintsScreen(authViewModel, navController, modifier)
        }

    }
}

enum class ScreenType(screen: String) {
    LOGIN("Tela de Login"),
    SINGUP("Tela de Cadastro"),
    SINGUPPASSWORD("Tela de Cadastro de Senha"),
    RECENTCOMPLAINTS("Reclamacoes recentes")
}