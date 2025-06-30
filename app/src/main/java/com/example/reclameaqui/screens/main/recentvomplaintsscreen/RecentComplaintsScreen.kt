package com.example.reclameaqui.screens.main.recentvomplaintsscreen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.R
import com.example.reclameaqui.screens.main.recentvomplaintsscreen.components.ComplaintCard
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.AzulFracoBackground
import com.example.reclameaqui.ui.theme.displayFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RecentComplaintsScreen(
    navController: NavController,
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .background(AzulFracoBackground)
            .fillMaxSize()
    ) {

        Column () {
            // Reclamações
            Text(text = stringResource(R.string.reclama_es_recentcomplaints),
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = displayFontFamily,
                color = AzulForteText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp))

            // Recentes
            Text(text = stringResource(R.string.recentes_recentcomplaints),
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

            // CardReclamação.
            // Futuro LazyColumn.
            ComplaintCard("Vinicius Mendes", "Olá, eu sou o vini", LocalDateTime.now())
            ComplaintCard("Edvaldo Correa", "Reclamando das reclamação mantendo a reclamação", LocalDateTime.now())
            ComplaintCard("Katia de Jesus", """
                Testando o app com textos exorbitantes e vendo como ficaria no resultado final.
                Isso não deixa de ser uma reclamação.
            """.trimIndent(), LocalDateTime.now())

        }
    }
}

@Preview
@Composable
fun RecentComplaintsScreenPreview() {
    val navControler = rememberNavController()
    RecentComplaintsScreen(navControler, modifier = Modifier)
}