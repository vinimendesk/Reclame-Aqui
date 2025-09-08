package com.example.reclameaqui.screens.main.postcomplaintscreen

data class PostComplaintsUiState(

    val complaint: String = "", // Dado da reclamação.
    val showErros: Boolean = false // Indica se pode mostrar a animação de erro.

) {

    val complainError: Boolean get() = showErros && complaint.isBlank()
    val isValid: Boolean get() = complaint.isNotBlank() // Se complaint não for vazio, está válido para postar

}
