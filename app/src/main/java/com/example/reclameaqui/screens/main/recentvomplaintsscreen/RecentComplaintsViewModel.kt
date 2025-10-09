package com.example.reclameaqui.screens.main.recentvomplaintsscreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.reclameaqui.data.ComplaintPost
import com.example.reclameaqui.data.ComplaintPostUi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class RecentComplaintsViewModel: ViewModel() {

    // Define RecentComplaintUiState como um estado mutável.
    private val _recentComplaintUiState = MutableStateFlow(RecentComplaintsUiState())

    // Coleta o último estado o convertendo em um StateFlow.
    val recentComplaintsUiState = _recentComplaintUiState.asStateFlow()

    // Carrega todas as reclamações.
    fun loadComplaints(databaseReference: DatabaseReference, context: Context) {
        // Sempre que houver uma alteração no banco, chame a função.
        databaseReference.addValueEventListener(
            // Cria um objeto para implementar a interface ValueEventListener.
            object: ValueEventListener {
                // Sobrescreve a função chamada quando há alteração nos dados.
                override fun onDataChange(snapshot: DataSnapshot) { // snapshot contém todos os dados do nó em específico.
                    snapshot.children.forEach {
                        onComplaintsEmpty()
                        // Converte o valor atual em uma classe de dados do tipo ComplaintPost.
                        val complaint = it.getValue(ComplaintPost::class.java)
                        // Busca o TIMESTAMP do nó postDate e converte para o time Long.
                        val timestamp = it.child("postDate").getValue(Long::class.java)
                        val localDateTime = timestamp?.let {
                            Instant.ofEpochMilli(it)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                        } ?: LocalDateTime.of(0, 0, 0, 0, 0)

                        complaint?.let {
                            val complaintPostUi = ComplaintPostUi(
                                id = complaint.id,
                                author = complaint.author,
                                text = complaint.text,
                                postDate = localDateTime,
                                isEdited = complaint.isEdited
                            )

                            onAddComplaint(complaintPostUi)
                            Log.d("FIREBASE_COMPLAINT", "complaint: $complaintPostUi")

                        }


                    }
                }
                // Sobrescreve a função chamada caso ocorra algum erro.
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        context,
                        "Erro ao carregar as reclamações",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    // Limpa os dados da lista de Complaints.
    fun onComplaintsEmpty() {
        _recentComplaintUiState.update {
            it.copy(complaintsList = emptyList())
        }
    }

    // Adicionar reclamação a lista atual.
    fun onAddComplaint(complaint: ComplaintPostUi) {
        val complaintsList = _recentComplaintUiState.value.complaintsList
        _recentComplaintUiState.update {
            it.copy(complaintsList = complaintsList + complaint)
        }
    }
}