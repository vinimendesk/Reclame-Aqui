package com.example.reclameaqui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.screens.authentication.login.LoginScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpPassword
import com.example.reclameaqui.screens.authentication.singup.SingUpScreen
import com.example.reclameaqui.screens.authentication.singup.SingUpViewModel
import com.example.reclameaqui.screens.main.bottomnavigationbar.BottomNavigationBar
import com.example.reclameaqui.screens.main.bottomnavigationbar.NavigationItemContentList
import com.example.reclameaqui.screens.main.familymembersscreen.FamilyMemberScreen
import com.example.reclameaqui.screens.main.postcomplaintscreen.PostComplaintsScreen
import com.example.reclameaqui.screens.main.profilescreen.ProfileScreen
import com.example.reclameaqui.screens.main.profilescreen.ProfileViewModel
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.RecentComplaintsScreen
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.RecentComplaintsViewModel
import com.google.firebase.database.DatabaseReference


@Composable
fun AppNavigation(
    authViewModel: AuthViewModel,
    databaseReference: DatabaseReference,
    modifier: Modifier) {

    val authState = authViewModel.authState.collectAsState()

    // Referência ao nó Users
    val userData = databaseReference.child("Users") // Busca o nó Users
    val postData = databaseReference.child("Posts") // Busca o nó Posts

    when (authState.value) {
        is AuthState.Authenticated -> MainNavigation(authViewModel, userData, postData, modifier)
        is AuthState.Unauthenticated -> AuthNavigation(authViewModel, databaseReference, modifier)
        is AuthState.Error -> AuthNavigation(authViewModel, databaseReference, modifier)
        is AuthState.Loading -> AuthNavigation(authViewModel, databaseReference,modifier)
    }

}

// Grafo para Authenticação.
@Composable
fun AuthNavigation(
    authViewModel: AuthViewModel,
    databaseReference: DatabaseReference,
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
            SingUpPassword(authViewModel, singUpViewModel, navController, databaseReference, modifier)
        }

    }
}

// Grafo para as telas principais.
@Composable
fun MainNavigation(
    authViewModel: AuthViewModel,
    userData: DatabaseReference,
    postData: DatabaseReference,
    modifier: Modifier
) {

    val navController = rememberNavController()

    // Retorna o estado autal de qual tela está na pilha de navegação.
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    // Acessamos a route do composable atual.
    val currentDestination = navBackStackEntry.value?.destination?.route
    // Procura no ScreenType, qual a route especifica atual, caso não encontre, retorne a RecentComplaints.
    val currentScreen = ScreenType.entries.find { it.name == currentDestination } ?: ScreenType.RECENTCOMPLAINTS

    // Instâncias de ViewModel e UiState
    val profileViewModel: ProfileViewModel = viewModel()
    val profileUiState = profileViewModel.profileUiState.collectAsState()

    val recentComplaintsViewModel: RecentComplaintsViewModel = viewModel()
    val recentComplaintsUiState = recentComplaintsViewModel.recentComplaintsUiState.collectAsState()

    val context = LocalContext.current

    // Buscar os dados do usuário atual.
    LaunchedEffect(Unit) {
        profileViewModel.loadCurrentUser(userData, context)
    }

    // Buscar dos posts das reclamações.
    LaunchedEffect(Unit) {
        recentComplaintsViewModel.loadComplaints(postData, context)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onTabPressed = { navController.navigate(it) },
                navigationItemContentList = NavigationItemContentList.getNavigationContentList(),
                modifier = Modifier.height(105.dp),
                currentScreen = currentScreen
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = ScreenType.RECENTCOMPLAINTS.name
        ) {

            // Tela reclamações.
            composable(ScreenType.RECENTCOMPLAINTS.name) {
                RecentComplaintsScreen(
                    recentComplaintsUiState = recentComplaintsUiState,
                    modifier = modifier.padding(paddingValues)
                )
            }

            // Tela integrantes da familia.
            composable(ScreenType.FAMILYMEMBERS.name) {
                FamilyMemberScreen(
                    authViewModel,
                    navController,
                    userData,
                    modifier.padding(paddingValues)
                )
            }

            // Tela postar reclamações.
            composable(ScreenType.MAKEACOMPLAINT.name) {
                PostComplaintsScreen(
                    profileUiState = profileUiState,
                    postDatabaseReference = postData,
                    context = context,
                    modifier = modifier.padding(paddingValues)
                )
            }

            // Tela perfil.
            composable(ScreenType.PROFILE.name) {
                ProfileScreen(
                    authViewModel = authViewModel,
                    profileUiState = profileUiState,
                    profileViewModel = profileViewModel,
                    databaseReference = userData,
                    context = context,
                    modifier = modifier.padding(paddingValues)
                )
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