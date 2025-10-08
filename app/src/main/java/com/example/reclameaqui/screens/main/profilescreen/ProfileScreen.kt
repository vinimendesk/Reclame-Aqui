package com.example.reclameaqui.screens.main.profilescreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reclameaqui.R
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.screens.main.profilescreen.components.EditInformationDialog
import com.example.reclameaqui.screens.main.profilescreen.components.SingOutDialog
import com.example.reclameaqui.screens.main.profilescreen.components.UserLikeInformation
import com.example.reclameaqui.screens.main.profilescreen.components.UserNameInformation
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.AzulFracoBackground
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.RoxoText
import com.example.reclameaqui.ui.theme.poppinsFontFamily
import com.google.firebase.database.DatabaseReference

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    databaseReference: DatabaseReference,
    modifier: Modifier
) {

    val profileViewModel: ProfileViewModel = viewModel()
    val profileUiState = profileViewModel.profileUiState.collectAsState()
    val currentUser = profileUiState.value.userProfile

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        profileViewModel.loadCurrentUser(databaseReference,context)
    }

    Box(
        modifier = modifier
            .background(AzulFracoBackground)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ----- CABEÇALHO -----
            // Text Eu, Edvaldo Correa
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 48.dp)
                    .fillMaxWidth()
            ) {
                // Eu,
                Text(text = stringResource(R.string.eu_profilescreen),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = poppinsFontFamily(),
                    color = AzulForteText,
                    textAlign = TextAlign.Center)
                // Edvaldo Correa
                Text(text = currentUser.name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = poppinsFontFamily(),
                    color = RoxoText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }

            // Divisor
            Spacer(modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .height(1.5.dp)
                .background(Color.Black)
                .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.PersonPin,
                    contentDescription = stringResource(R.string.imagem_do_autor_complaintcard),
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center)
                )

                    // Botão "Sair"
                    Button(
                        onClick = { profileViewModel.openSingOutDialog() },
                        content = { Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(R.string.sair_profileScreen),
                                fontFamily = poppinsFontFamily(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp)
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = stringResource(R.string.sair_do_usu_rio_profileScreen),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = RoxoButton),
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.BottomEnd)
                    )
            }


            UserNameInformation(
                editInformation = {
                    profileViewModel.chooseInformation(1)
                    profileViewModel.onTextEditInformation("")
                    profileViewModel.openEditInformationDialog()
                },
                content = currentUser.name,
                type = "Seu nome"
            )

            UserLikeInformation(
                editInformation = {
                    profileViewModel.chooseInformation(2)
                    profileViewModel.onTextEditInformation("")
                    profileViewModel.openEditInformationDialog()
                },
                content = currentUser.whatLikeMore,
                type = "O que mais agrada?"
            )

            UserLikeInformation(
                editInformation = {
                    profileViewModel.chooseInformation(3)
                    profileViewModel.onTextEditInformation("")
                    profileViewModel.openEditInformationDialog()
                },
                content = currentUser.whatDislikeMore,
                type = "O que mais odeia?"
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Você postou 498 reclamações.
            Text(
                text = stringResource(
                    R.string.voce_postou_x_reclamacoes_profileScreen,
                    currentUser.complaintsCount
                ),
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                fontSize = 16.sp,
                color = AzulForteText,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Visualizar minhas reclamações
            Button(
                onClick = { /*Visualizar reclamações*/ },
                content = {
                    Text(
                        text = stringResource(R.string.visualizar_minhas_reclama_es_profileScreen),
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily(),
                        fontWeight = FontWeight.Black
                ) },
                colors = ButtonDefaults.buttonColors(containerColor = RoxoButton),
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )

            // Divisor Tela / BottomNavigationBar
            Box (
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
                        .height(1.5.dp)
                        .background(Color.Black)
                        .fillMaxWidth()
                )
            }
        }
    }


    // ---------- CAIXAS DE DIÁLOGO ---------
    // Verifica se foi aberto o SingOutDialog
    if (profileUiState.value.openSingOutDialog == true) {
        SingOutDialog(
            authViewModel,
            { profileViewModel.closeSingOutDialog() },
            Modifier
        )
    }

    if (profileUiState.value.openEditInformationDialog == true) {
        EditInformationDialog(
            onDismissRequest = {
                profileViewModel.closeEditInformationDialog()
                profileViewModel.chooseInformation(0)
            },
            textEditInformation = profileUiState.value.textEditInformationDialog,
            onTextEditChange = { text -> profileViewModel.onTextEditInformation(text) },
            numberInformation = profileUiState.value.numberInformation,
            editInformationErrorDialog = profileUiState.value.editInformationErrorDialog,
            isValid = profileUiState.value.isValid,
            showValidationErrors = { profileViewModel.showValidationErros(context) },
            editUserInformation = { profileViewModel.editUserInformation(
                chooseOption = profileUiState.value.numberInformation,
                databaseReference = databaseReference,
                context = context,
                userId = profileUiState.value.userProfile.id,
                newValue = profileUiState.value.textEditInformationDialog
            ) },
            modifier = modifier
        )
    }
}
