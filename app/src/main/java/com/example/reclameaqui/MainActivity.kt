package com.example.reclameaqui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reclameaqui.auth.AuthViewModel
import com.example.reclameaqui.navigation.AppNavigation
import com.example.reclameaqui.ui.theme.ReclameAquiTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cria ou usa o nó/node(SCHEMA) Users.
        databaseReference = FirebaseDatabase.getInstance().getReference("Reclame Aqui")
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()
        setContent {
            ReclameAquiTheme {
                AppNavigation(authViewModel, databaseReference, modifier = Modifier)
            }
        }
    }
}
