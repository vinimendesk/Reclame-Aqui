package com.example.reclameaqui.screens.main.familymembersscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.reclameaqui.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FamilyMembersViewModel: ViewModel() {

    // Coleta os estados do UiState.
    private val _familyMembersUiState = MutableStateFlow(FamilyMembersUiState())

    // Coleta o estado atual do UiState.
    val familyMembersUiState = _familyMembersUiState.asStateFlow()

    // Função para buscar todos os usuários.
    fun loadUsers(databaseReference: DatabaseReference, context: Context) {
        databaseReference.addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onUsersListEmpty()
                    snapshot.children.forEach{
                        val user = it.getValue(User::class.java)
                        user?.let { onUsersListAdd(user) }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        context,
                        "Erro ao carregar o usuário",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    // Limpa os usuários atuais.
    fun onUsersListEmpty() {
        _familyMembersUiState.update {
            it.copy(userList = emptyList())
        }
    }

    // Adiciona items a lista de usuários.
    fun onUsersListAdd(user: User) {
        val usersList = _familyMembersUiState.value.userList
        _familyMembersUiState.update {
            it.copy(userList = (usersList + user))
        }
    }

}