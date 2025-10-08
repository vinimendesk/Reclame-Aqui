package com.example.reclameaqui.screens.main.postcomplaintscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.reclameaqui.data.ComplaintPost
import com.google.firebase.database.DatabaseReference
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