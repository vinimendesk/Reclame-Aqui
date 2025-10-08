package com.example.reclameaqui.screens.main.profilescreen

import com.example.reclameaqui.data.User

data class ProfileUiState(
    val userProfile: User = User(),
    val openSingOutDialog: Boolean = false,
    val openEditInformationDialog: Boolean = false,
    val textEditInformationDialog: String = "",
    val numberInformation: Int = 0,
    val showErros: Boolean = false
) {
    // Verifica se o texto no TextField no editInformationDialog está vazio.
    val editInformationErrorDialog: Boolean get() = showErros && textEditInformationDialog.isBlank()
    val isValid: Boolean get() = textEditInformationDialog.isNotBlank()
}