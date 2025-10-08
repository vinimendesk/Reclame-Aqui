package com.example.reclameaqui.screens.main.postcomplaintscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reclameaqui.R
import com.example.reclameaqui.animations.errorContainerColor
import com.example.reclameaqui.animations.errorTextColor
import com.example.reclameaqui.animations.shakeAnimation
import com.example.reclameaqui.data.ComplaintPost
import com.example.reclameaqui.screens.main.profilescreen.ProfileUiState
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.LaranjaButton
import com.example.reclameaqui.ui.theme.LaranjaText
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.poppinsFontFamily
import com.google.firebase.database.DatabaseReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PostComplaintsScreen(
    postDatabaseReference: DatabaseReference,
    profileUiState: State<ProfileUiState>,
    context: Context,
    modifier: Modifier
) {

    val postComplaintViewModel: PostComplaintsViewModel = viewModel()
    val postComplaintsUiState by postComplaintViewModel.uiState.collectAsState()
    val complaint = postComplaintsUiState.complaint
    val complaintError = postComplaintsUiState.complainError
    val complaintColor = errorContainerColor(complaintError, null)
    val complaintText = errorTextColor(complaintError, null)
    val complaintShake = shakeAnimation(complaintError, null)

    Box(
        modifier = modifier
            .background(RosaBackground)
            .fillMaxSize()
    ) {

        // ---------- CABEÇALHO ----------
        Column {
            // Reclamações
            Text(text = stringResource(R.string.faca_uma_postComplaints),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp))

            // Recentes
            Text(text = stringResource(R.string.reclamacao_postComplaints),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp))

            // Linha
            Spacer(modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .height(1.5.dp)
                .background(Color.Black)
                .fillMaxWidth()
                .padding(bottom = 12.dp)
            )

            Spacer(modifier = Modifier
                .height(120.dp))

            // ---------- Adicionar reclamação ----------
            // O que te incomoda hoje?
            Text(text = stringResource(R.string.o_que_te_incomoda_hoje_postComplaints),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                color = LaranjaText,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 16.dp)
                    .fillMaxWidth()
            )

            // TextField E-mail.
            TextField(value = complaint,
                onValueChange = { complaint ->
                    postComplaintViewModel.onComplaintChange(complaint)
                },
                placeholder = { Text(
                    text = stringResource(R.string.escreva_o_que_te_incomoda_postComplaints),
                    color = complaintText,
                    fontFamily = poppinsFontFamily(),
                    modifier = Modifier
                        .padding(bottom = 130.dp)
                ) },
                singleLine = false,
                // isError = emailError, Define o estado de error do TextField
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = complaintColor,
                    unfocusedContainerColor = complaintColor,
                    disabledContainerColor = complaintColor,
                    errorContainerColor = complaintColor
                ),
                modifier = Modifier
                    .graphicsLayer { translationX = complaintShake }
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(start = 24.dp, end = 24.dp, bottom = 8.dp))

            Spacer(modifier = Modifier.height(120.dp))

            // Visualizar minhas reclamações
            Button(
                onClick = {

                    val complaintPostId = postDatabaseReference.push().key
                    val dateTimeNow = LocalDateTime.now().toString()
                    val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

                    postComplaintViewModel.showValidationErros(context)

                    if (complaintPostId != null && postComplaintsUiState.isValid) {
                        postComplaintViewModel.addComplaint(
                            complaintPost = ComplaintPost(
                                id = complaintPostId,
                                author = profileUiState.value.userProfile.name,
                                text = postComplaintsUiState.complaint,
                                postDate = dateTimeNow.format(dateTimeFormat),
                                isEdited = false
                            ),
                            context = context,
                            databaseReference = postDatabaseReference
                        )
                    }
                    postComplaintViewModel.onComplaintChange("")
                },
                content = {
                    Text(
                        text = stringResource(R.string.fazer_reclama_o_makeComplaint),
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily(),
                        fontWeight = FontWeight.Black
                    ) },
                colors = ButtonDefaults.buttonColors(containerColor = LaranjaButton),
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )

            // Divisor Tela/BottomNavigationBar
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
}

/*
@Preview
@Composable
fun PostComplaintsScreenPreview() {

    PostComplaintsScreen(Modifier)

}*/
