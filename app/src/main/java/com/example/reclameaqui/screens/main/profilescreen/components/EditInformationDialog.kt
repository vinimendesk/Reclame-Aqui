package com.example.reclameaqui.screens.main.profilescreen.components

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.ui.theme.LaranjaButton
import com.example.reclameaqui.ui.theme.LaranjaText
import com.example.reclameaqui.ui.theme.poppinsFontFamily
import kotlinx.coroutines.delay

@Composable
fun EditInformationDialog(
    onDismissRequest: () -> Unit,
    textEditInformation:  String,
    onTextEditChange: (String) -> Unit,
    numberInformation: Int,
    editInformationErrorDialog: Boolean,
    isValid: Boolean,
    showValidationErrors: () -> Unit,
    editUserInformation: () -> Unit,
    modifier: Modifier
) {

    // Definir animações de error no TextField.
    val editTextError = editInformationErrorDialog
    val editTextShake = shakeAnimation(editTextError, null)
    val editTextColor = errorContainerColor(editTextError, null)
    val editText = errorTextColor(editTextError, null)

    // Definir qual o dado a ser alterado.
    val textDialog = when(numberInformation) {
        1 -> stringResource(R.string.seu_nome_editInformationDialog)
        2 -> stringResource(R.string.o_que_mais_gosta_editInformationDialog)
        3 -> stringResource(R.string.o_que_mais_odeia_editInformationDialog)
        else -> stringResource(R.string.bug_do_aplicativo_editInformationDialog)
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .height(315.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
        ) {
            // ----- BOTÃO DE FECHAR -----
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        onDismissRequest()
                    }
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
            // Deseja editar "Informação"?.
            Text(
                text = stringResource(R.string.deseja_editar_informacao_editInformationDialog, textDialog),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = LaranjaText,
                fontFamily = poppinsFontFamily(),
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

            // TextField EditInformation.
            TextField(
                value = textEditInformation,
                onValueChange = { text -> onTextEditChange(text) },
                placeholder = { Text(
                    text = textDialog,
                    color = editText,
                    fontFamily = poppinsFontFamily()
                ) },
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = editTextColor,
                    unfocusedContainerColor = editTextColor,
                    disabledContainerColor = editTextColor,
                    errorContainerColor = editTextColor
                ),
                modifier = Modifier
                    .graphicsLayer(translationX = editTextShake)
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 48.dp, top = 24.dp)
            )

            // Botão confirmar alteração
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Botão "Confirmar alteração"
                Button(
                    onClick = {

                        showValidationErrors()

                        if (isValid) {
                            editUserInformation()
                            onDismissRequest()
                        }

                    },
                    content = {
                        Text(text = stringResource(R.string.confirmar_alteracao_editInformationDialog),
                            fontFamily = poppinsFontFamily(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LaranjaButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                )
            }
        }
    }

}