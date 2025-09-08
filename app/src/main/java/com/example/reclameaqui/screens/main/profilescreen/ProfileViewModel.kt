package com.example.reclameaqui.screens.main.profilescreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    // Coleta do StateFlow do _profileUiState
    val profileUiState = _profileUiState.asStateFlow()

    // Abrir a caixa de diálogo de singOut.
    fun openSingOutDialog() {
        _profileUiState.update {
            it.copy(openSingOutDialog = true)
        }
    }

    // Fechar caixa de diálogo de singOut.
    fun closeSingOutDialog(){
        _profileUiState.update {
            it.copy(openSingOutDialog = false)
        }
    }

}