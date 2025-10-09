package com.example.reclameaqui.data

import java.time.LocalDateTime

data class ComplaintPost(
    val id: String = "",
    val author: String = "",
    val text: String = "",
    val postDate: Any? = null, // Recebe qualquer tipo de dados, já que para Post o firebase envia como Map<String, String> e para Read lemos como LONG.
    val isEdited: Boolean = false,
    /*val imageProfile*/
)
