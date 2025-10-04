package com.example.reclameaqui.screens.main.profilescreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reclameaqui.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    // Coleta do StateFlow do _profileUiState
    val profileUiState = _profileUiState.asStateFlow()

    // ---------- FUNÇÃO ATUALIZAR USUÁRIO ATUAL ----------
    // Buscar dados do usuário atual.
    fun loadCurrentUser(databaseReference: DatabaseReference, context: Context) {
        // Busque o usuário atual.
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Se não houver usuário logado, sai da função.
        if (currentUser == null) {
            Toast.makeText(context, "Nenhum usuário logado", Toast.LENGTH_SHORT).show()
            return
        }

        // Referência ao nó do usuário atual pelo UID.
        val userRef = databaseReference.child(currentUser.uid)

        // Qualquer alteração no usuário atual, realize o callback.
        userRef.addValueEventListener(
            // Cria um objeto e implementa a interface ValueEventListener
            object: ValueEventListener  {
                // Sobrecresve a função quando há alteração nos dados.
                // snapshot busca todos os dados do nó
                override fun onDataChange(snapshot: DataSnapshot) {

                    // Busca o dado da snapshot e converte para a classe User.
                    val user = snapshot.getValue(User::class.java)

                    // Se user não for nulo.
                    user?.let {
                        // Exiba o uusário atual no Log.
                        Log.d("FIREBASE_USER", "Usuário atual: $it")
                        // Atualize o UiState.
                        onUserChange(it)
                    // Se user for nulo.
                    } ?: run {
                        Toast.makeText(
                            context,
                            "Usuário não encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                // Se ocorrer algum erro.
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

    // Editar o nome do usuário.
    fun editNameUser(
        databaseReference: DatabaseReference,
        context: Context,
        user: User
    ) {
        val newUserDataMap = mapOf(
            "name" to user.name
        )

        databaseReference.child(user.id).updateChildren(newUserDataMap)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Nome de usuário alterado com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Erro ao editar nome de usuário",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun onUserChange(user: User) {
        _profileUiState.update {
            it.copy(userProfile = user)
        }
    }

    // ---------- FUNÇÃO CAIXA DE DIÁLOGO DE EDITINFORMATIONDIALOG ---------
    // Abrir caixa de diálogo edit information.
    fun openEditInformationDialog() {
        _profileUiState.update {
            it.copy(openEditInformationDialog = true)
        }
    }

    // Fechar caixade diálogo edit information.
    fun closeEditInformationDialog() {
        _profileUiState.update {
            it.copy(openEditInformationDialog = false)
        }
    }

    // Mudança de texto no TextField.
    fun onTextEditInformation(text: String) {
        _profileUiState.update {
            it.copy(textEditInformationDialog = text)
        }
    }

    // Escolher o tipo de dado a ser alterado.
    fun chooseInformation(option: Int) {
        _profileUiState.update {
            it.copy(numberInformation = option)
        }
    }

    fun showValidationErros(context: Context) {
        _profileUiState.update {
            it.copy(showErros = true)
        }

        if (profileUiState.value.editInformationErrorDialog) {
            Toast.makeText(
                context,
                "Não é possível editar com valores vazios",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModelScope.launch {
            _profileUiState.update {
                delay(1000)
                it.copy(showErros = false)
            }
        }
    }

    // ---------- FUNÇÃO CAIXA DE DIÁLOGO DE SINGOUT ----------
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