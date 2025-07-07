package com.example.reclameaqui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.screens.authentication.login.LoginScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpPassword
import com.example.reclameaqui.screens.authentication.singup.SingUpScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpViewModel
import com.example.reclameaqui.screens.main.bottomnavigationbar.BottomNavigationBar
import com.example.reclameaqui.screens.main.bottomnavigationbar.NavigationItemContentList
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.RecentComplaintsScreen


@Composable
fun AppNavigation(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier) {

    val authState = authViewModel.authState.collectAsState()

    when (authState.value) {
        is AuthState.Authenticated -> MainNavigation(navController, authViewModel, modifier)
        is AuthState.Unauthenticated -> AuthNavigation(authViewModel, modifier)
        else -> Unit
    }

}

// Grafo para Authenticação.
@Composable
fun AuthNavigation(
    authViewModel: AuthViewModel,
    modifier: Modifier
) {

    val navController = rememberNavController()
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

    }
}

// Grafo para as telas principais.
@Composable
fun MainNavigation(
    navController: NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier
) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onTabPressed = { navController.navigate(it) },
                navigationItemContentList = NavigationItemContentList.getNavigationContentList(),
                modifier = Modifier.height(105.dp)
                currentScreen = /*currentScreen*/,
            )
        }
    ) { paddingValues ->

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = ScreenType.RECENTCOMPLAINTS.name
        ) {

            // Tela reclamações.
            composable(ScreenType.RECENTCOMPLAINTS.name) {
                RecentComplaintsScreen(authViewModel, navController, modifier.padding(paddingValues))
            }

        }
    }
}

enum class ScreenType(val screen: String) {
    // Telas de Authenticação.
    LOGIN("Tela de Login"),
    SINGUP("Tela de Cadastro"),
    SINGUPPASSWORD("Tela de Cadastro de Senha"),

    // Telas Principais.
    RECENTCOMPLAINTS("Reclamacoes recentes"),
    FAMILYMEMBERS("Integrantes da Familia"),
    MAKEACOMPLAINT("Faça uma Reclamação"),
    PROFILE("Perfil")
}