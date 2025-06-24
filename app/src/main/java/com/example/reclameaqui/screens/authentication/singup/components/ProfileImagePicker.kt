package com.example.reclameaqui.screens.authentication.singup.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun ProfileImagePicker(/*imageUrl: String?, onImageSelected: (Uri) -> Unit*/) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
        /*uri: Uri? ->
        * uri?.let { onImageSelected(it) }*/
    }

}