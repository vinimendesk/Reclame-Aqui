package com.example.reclameaqui.screens.authentication.singup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reclameaqui.data.User
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingUpViewModel: ViewModel() {

    // versão mutável e observável do LoginUiState.
    private val _singUpUiState = MutableStateFlow(SingUpUiState())
    // versão imutável e exposta do LoginUiState.
    val singUpUiState = _singUpUiState.asStateFlow()

    // FUNÇÕES TEXTFIELDS
    // ----- SingUp Screen -----
    fun onEmailChange(email: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            email = email
        )
    }

    fun onNameChange(name: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            name = name
        )
    }

    fun onWhatMoreLikeChange(text: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            whatMoreLike = text
        )
    }

    fun onWhatMoreDislikeChange(text: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            whatMoreDislike = text
        )
    }

    // ----- SingUpPassword Screen -----
    fun onPasswordChange(password: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            password = password
        )
    }

    fun onPasswordAgainChange(password: String) {
        _singUpUiState.value = _singUpUiState.value.copy(
            passwordAgain = password
        )
    }

    fun showValidationErrosFalse() {
        _singUpUiState.value = _singUpUiState.value.copy(
            showErrors = false
        )
    }

    fun showValidationErrors() {
        _singUpUiState.update {
            it.copy(showErrors = true)
        }

        // Aguarda 2 segundos em uma corrotina e retorna falso.
        viewModelScope.launch {
            delay(1000)
            _singUpUiState.update { it.copy(showErrors = false) }
        }
    }

    // FUNÇÕES CRUD - FIREBASE
    fun addUser(databaseReference: DatabaseReference, user: User) {
        // Cria o child "Users" -> "user".
        databaseReference.child("Users").child(user.id).setValue(user)
        // Se child criado, log de sucesso.
            .addOnCompleteListener {
                Log.i("UserManager","Usuário cadastrado com sucesso") // Log de informação.
            }
            .addOnFailureListener { exception ->
                Log.e("UserManager", "Erro ao cadastro usuário: ${exception.message}") // log de error.
            }
    }

}