package com.example.reclameaqui.screens.authentication.singup.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reclameaqui.R

@Composable
fun ProfileImagePicker(/*imageUrl: String?,
onImageSelected: (Uri) -> Unit*/
modifier: Modifier = Modifier) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
        /*uri: Uri? ->
        * uri?.let { onImageSelected(it) }*/
    }

    Box(modifier = modifier
        .size(120.dp)
        .clickable { launcher.launch("image/*") }) {

        // Ícone de câmera
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = stringResource(R.string.alterar_imagem_profileimagepicker),
                tint = Color.Black,
                modifier = Modifier.size(32.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            /*if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            } else {*/
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(R.string.selecionar_imagem_de_perfil_profileimagepicker),
                modifier = Modifier.size(64.dp)
            )

        }
    }
}