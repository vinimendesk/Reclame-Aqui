package com.example.reclameaqui.screens.authentication.singup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reclameaqui.R
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.ui.theme.AzulForteText
import com.example.reclameaqui.ui.theme.RosaBackground
import com.example.reclameaqui.ui.theme.RoxoButton
import com.example.reclameaqui.ui.theme.displayFontFamily

@Composable
fun SingUpPassword(navController: NavController,
    modifier: Modifier) {

    Box(modifier = modifier
        .fillMaxSize()
        .background(RosaBackground)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 90.dp)
            ) {
                // Botão de retornar a página.
                IconButton(
                    onClick = { navController.navigate(ScreenType.SINGUP.name) },
                    modifier = Modifier
                        .padding(start = 10.dp, top = 8.dp, bottom = 60.dp)
                        .size(40.dp)

                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.retorna_a_tela_de_cadastro_singuppassword),
                        modifier = Modifier
                            .size(40.dp)
                    )
                }

                // Text "CADASTRE SUA"
                Text(
                    text = stringResource(R.string.cadastre_sua_singuppassword),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = displayFontFamily,
                    color = AzulForteText,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                // Text "CADASTRE SUA"
                Text(
                    text = stringResource(R.string.senha_singuppassword),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = displayFontFamily,
                    textDecoration = TextDecoration.Underline,
                    color = RoxoButton,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun SingUpPasswordPreview() {
    val navController = rememberNavController()
    SingUpPassword(navController, modifier = Modifier)
}