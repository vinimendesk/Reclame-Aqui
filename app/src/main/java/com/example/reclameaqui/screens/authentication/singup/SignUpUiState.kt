package com.example.reclameaqui.screens.authentication.singup

data class SingUpUiState(

    // ----- SINGUP SCREEN -----
    val name: String = "",
    val email: String = "",
    val whatMoreLike: String = "",
    val whatMoreDislike: String = "",
    val showErrors: Boolean = false,

    // ----- SINGUPPASSWORD SCREEN -----

    val password: String = "",
    val passwordAgain: String = "",
    val passwordVisible: Boolean = false // controla quando o aplicativo deve mostrar a senha.
) {

    // ----- SINGUP SCREEN -----
    val nameError: Boolean get() = showErrors && name.isBlank()
    val emailError: Boolean get() = showErrors && email.isBlank()
    /*val whatMoreLikeError: Boolean get() = showErrors && whatMoreLike.isBlank()
    val whatMoreDislikeError: Boolean get() = showErrors && whatMoreDislike.isBlank()*/
    val isValidSingUP: Boolean get() = name.isNotBlank() && email.isNotBlank()

    // ----- SINGUPPASSWORD SCREEN -----
    val passwordError: Boolean get() = showErrors && password.isBlank()
    val passwordAgainError: Boolean get() = showErrors && passwordAgain.isBlank()
    val incorrectPasswordAgain: Boolean get() = showErrors && passwordAgain != password
    val isValidSingUpPassword: Boolean get() = password.isNotBlank() && passwordAgain.isNotBlank()
            && password == passwordAgain

}