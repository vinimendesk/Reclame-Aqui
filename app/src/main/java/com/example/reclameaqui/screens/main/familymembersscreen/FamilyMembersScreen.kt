package com.example.reclameaqui.screens.main.familymembersscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.R
import com.example.reclameaqui.auth.AuthState
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.data.User
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.screens.main.familymembersscreen.components.FamilyMemberCard
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.components.ComplaintCard
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.AzulFracoBackground
import com.example.reclameaqui.ui.theme.displayFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun FamilyMemberScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier
) {

//    val authState = authViewModel.authState.collectAsState()

//    LaunchedEffect(authState.value) {
//        when(authState.value) {
//            is AuthState.Unauthenticated -> {
//                navController.navigate(ScreenType.LOGIN.name) {
//                    popUpTo(ScreenType.RECENTCOMPLAINTS.name) { inclusive = true }
//                }
//            }
//            else -> Unit
//        }
//    }

    Box(
        modifier = modifier
            .background(AzulFracoBackground)
            .fillMaxSize()
    ) {

        Column {
            // Reclamações
            Text(text = stringResource(R.string.integrantes_da_familymemberscreen),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = displayFontFamily,
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp))

            // Recentes
            Text(text = stringResource(R.string.familia_familymemberscreen),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = displayFontFamily,
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

            // Card integrantes da família.
            // Futuro LazyColumn.
            FamilyMemberCard(
                User(
                    name = "Vinicius Mendes",
                    whatLikeMore = "Jogar vôlei",
                    whatDislikeMore = "Mexer em minhas coisas",
                    complaintsCount = 1
                )
            )
            FamilyMemberCard(
                User(
                    name = "Edvaldo Correa",
                    whatLikeMore = "Assistir novela",
                    whatDislikeMore = "Perderem as chaves",
                    complaintsCount = 1
                )
            )

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

@Preview
@Composable
fun FamilyMemberScreenPreview() {
    val authViewModel: AuthViewModel = viewModel()
    val navControler = rememberNavController()
    FamilyMemberScreen(authViewModel ,navControler, modifier = Modifier)
}