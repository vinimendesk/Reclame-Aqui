package com.example.reclameaqui.screens.authentication.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val showErrors: Boolean = false, // controla se erro nos textfields devem ser mostrados.
    val passwordVisible: Boolean = false // controla quando o aplicativo deve mostrar a senha.
) {
    val emailError: Boolean get() = showErrors && email.isBlank()
    val passwordError: Boolean get() = showErrors && password.isBlank()
    val isValid: Boolean get() = email.isNotBlank() && password.isNotBlank()
}