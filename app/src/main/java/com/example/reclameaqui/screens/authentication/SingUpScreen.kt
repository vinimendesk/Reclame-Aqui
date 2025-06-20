package com.example.reclameaqui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reclameaqui.R
import com.example.reclameaqui.ui.theme.RosaBackground

@Composable
fun SingUpScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(RosaBackground)) {
        
        // Botão de retornar a página.
        IconButton(
            onClick = { /* Navegar para LoginScreen */ },
            modifier = Modifier
                .size(40.dp)
        )  {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.retornar_a_tela_de_login_singupscreen),
                modifier = Modifier
                    .size(40.dp)
            )
        }

    }

}

@Preview
@Composable
fun SingUpScreenPreview() {
    SingUpScreen()
}