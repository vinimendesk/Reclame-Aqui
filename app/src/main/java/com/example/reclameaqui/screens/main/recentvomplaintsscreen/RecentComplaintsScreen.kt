package com.example.reclameaqui.screens.main.recentvomplaintsscreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reclameaqui.R
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.components.ComplaintCard
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.AzulFracoBackground
import com.example.reclameaqui.ui.theme.poppinsFontFamily
import com.google.firebase.database.DatabaseReference
import java.time.LocalDateTime

@Composable
fun RecentComplaintsScreen(
    recentComplaintsUiState: State<RecentComplaintsUiState>,
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .background(AzulFracoBackground)
            .fillMaxSize()
    ) {

        Column {
            // Reclamações
            Text(text = stringResource(R.string.reclama_es_recentcomplaints),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp))

            // Recentes
            Text(text = stringResource(R.string.recentes_recentcomplaints),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = poppinsFontFamily(),
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp))

            Spacer(modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .height(1.5.dp)
                .background(Color.Black)
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                )

            LazyColumn(
                modifier = Modifier
                    .weight(1f) // ocupa o espaço restante da tela
                    .fillMaxWidth()
            ) {
                items(recentComplaintsUiState.value.complaintsList.size) { post ->
                    val complaintPost = recentComplaintsUiState.value.complaintsList[post]
                    ComplaintCard(
                        complaintPost,
                        false,
                        {}
                    )
                }
            }

            Box (
                contentAlignment = Alignment.BottomCenter,
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
fun RecentComplaintsScreenPreview() {
    val authViewModel: AuthViewModel = viewModel()
    val navController = rememberNavController()
    RecentComplaintsScreen(authViewModel ,navController, modifier = Modifier)
}*/
