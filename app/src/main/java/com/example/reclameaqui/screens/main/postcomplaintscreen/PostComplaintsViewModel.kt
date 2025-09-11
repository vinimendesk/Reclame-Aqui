package com.example.reclameaqui.screens.main.postcomplaintscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostComplaintsViewModel : ViewModel() {

    // Coleta os estados de PostCompçaintsUiState
    private val _uiState = MutableStateFlow(PostComplaintsUiState())
    // StateFlow retorna o número resultado do Flow.
    val uiState = _uiState.asStateFlow()

    fun onComplaintChange(complaint: String) {
        _uiState.update {
            it.copy(complaint = complaint)
        }
    }

}