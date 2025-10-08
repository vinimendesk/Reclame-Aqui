package com.example.reclameaqui.screens.main.familymembersscreen

import android.content.Context
import android.util.Log
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
        // Sempre que houver alterações no banco, chame a função dentro de addValueEventListener
        databaseReference.addValueEventListener(
            // Cria um objeto que implementa a interface ValueEventListener
            object: ValueEventListener {
                // Sobrescrever a função chamada quando há alterações nos dados.
                // snapshot contém todos os dados no nó específico.
                override fun onDataChange(snapshot: DataSnapshot) {
                    onUsersListEmpty()
                    // Busca cada dado daquele nó.
                    snapshot.children.forEach{
                        // Converte o valor daquele nó em uma classe do tipo User.
                        val user = it.getValue(User::class.java)
                        Log.d("FIREBASE_USER", "User: $user")
                        // Se user não for nulo, chame a função de adicionar usu[ario a lista.
                        user?.let { onUsersListAdd(user) }
                    }
                }

                // Sobrescrever a função, caso ocorra um erro.
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