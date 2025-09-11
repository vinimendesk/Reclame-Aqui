package com.example.reclameaqui.screens.main.profilescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reclameaqui.R
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.ui.theme.LaranjaButton
import com.example.reclameaqui.ui.theme.LaranjaText
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.poppinsFontFamily

@Composable
fun SingOutDialog(
    authViewModel: AuthViewModel,
    onDismissRequest: () -> Unit,
    modifier: Modifier) {

    val authState = authViewModel.authState.collectAsState()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
        ) {
            // ----- BOTÃO DE FECHAR -----
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { onDismissRequest() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.fechar_caixa_de_di_logo_singOutDialog),
                        tint = Color.White
                    )
                }
            }
        }

        // ----- ELEMENTOS CAIXA DE DIÁLOGO -----
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            // Tem certeza que quer sair?
            Text(
                text = stringResource(R.string.tem_certeza_que_quer_sair_SingOutDialog),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = LaranjaText,
                fontFamily = poppinsFontFamily(),
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )
            // Botões sim e não
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Botão "Sim"
                Button(
                    enabled = authState.value != AuthState.Loading,
                    onClick = { authViewModel.signOut() },
                    content = {
                        Text(text = stringResource(R.string.sim_singOutDialog),
                            fontFamily = poppinsFontFamily(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LaranjaButton),
                )
                // Botão "Não"
                Button(
                    onClick = { onDismissRequest() },
                    content = {
                        Text(text = stringResource(R.string.n_o_singOutDialog),
                            fontFamily = poppinsFontFamily(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LaranjaButton),
                )
            }
        }
    }

}

@Preview
@Composable
fun SingOutDialogPreview(){
    val authViewModel: AuthViewModel = viewModel()
    SingOutDialog(authViewModel, {}, Modifier)
}