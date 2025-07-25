package com.example.reclameaqui.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.reclameaqui.R
import com.example.reclameaqui.navigation.ScreenType
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    // Armazena uma instância do Firebase.
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthState()
    }

    // Função que verifica se o usuário está logado.
    fun checkAuthState() {
        // Se não existe usuário atual defina como Unauthenticated, caso contrário defina como Authenticated.
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    // Função de Login.
    fun login(email: String, password: String, context: Context) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error(context.getString(R.string.email_ou_senha_n_o_podem_estarem_vazios_Login))
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message?: context.getString(R.string.algo_deu_errado_Login))
                }
            }

    }

    // Função de cadastro.
    fun singUp(email: String, password: String, context: Context, navController: NavController) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error(context.getString(R.string.email_ou_senha_n_o_podem_estarem_vazios_Login))
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(ScreenType.LOGIN.name)
                    Toast.makeText(
                        context,
                        "Usuário cadastrado com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    _authState.value = AuthState.Error(task.exception?.message?:context.getString(R.string.algo_deu_errado_Login))
                }
            }
    }

    // Função de alterar o authState.
    fun onAuthStateChange(authState: AuthState) {
        _authState.value = authState
    }

}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}