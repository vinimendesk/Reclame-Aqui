package com.example.reclameaqui.screens.authentication.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    // versão mutável e observável do LoginUiState.
    private val _loginUiState = MutableStateFlow(LoginUiState())
    // versão imutável e exposta do LoginUiState.
    val loginUiState = _loginUiState.asStateFlow()

    // FUNÇÕES TEXTFIELDS
    fun onEmailChange(email: String) {
        _loginUiState.value = _loginUiState.value.copy(
            email = email
        )
    }

    fun onPasswordChange(password: String) {
        _loginUiState.value = _loginUiState.value.copy(
            password = password
        )
    }

    fun showValidationErrors() {
        _loginUiState.update { it.copy(showErrors = true) }
    }

}