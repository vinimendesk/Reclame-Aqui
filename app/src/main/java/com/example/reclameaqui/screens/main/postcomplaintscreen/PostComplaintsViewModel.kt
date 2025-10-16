package com.example.reclameaqui.screens.main.postcomplaintscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reclameaqui.data.ComplaintPost
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun showValidationErros(context: Context) {
        _uiState.update {
            it.copy(showErros = true)
        }

        if (uiState.value.complainError) {
            Toast.makeText(
                context,
                "Não é possível adicionar um post vazio.",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModelScope.launch {
            _uiState.update {
                delay(1000)
                it.copy(showErros = false)
            }
        }
    }

    // ---------- FIREBASE FUCTIONS ----------
    fun addComplaint(
        complaintPost: ComplaintPost,
        context: Context,
        databaseReference: DatabaseReference
    ) {

        // É criado um novo child caso não exista o id. Se criado, será usado o mesmo child.
        databaseReference.child(complaintPost.id).setValue(complaintPost)
            .addOnCompleteListener {
                Toast.makeText(
                    context,
                    "Reclamação adicionada com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Erro ao adicionar reclamação.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}