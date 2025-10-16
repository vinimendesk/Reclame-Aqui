package com.example.reclameaqui.screens.main.mycomplaintscreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference

class MyComplaintViewModel: ViewModel() {

    fun onDeletePost(
        databaseReference: DatabaseReference,
        context: Context,
        postId: String
    ) {
        databaseReference.child(postId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Post removido com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Falha ao remover o post.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}