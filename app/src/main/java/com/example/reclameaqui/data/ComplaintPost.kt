package com.example.reclameaqui.data

import java.time.LocalDateTime

data class ComplaintPost(
    val id: String = "",
    val author: String = "",
    val text: String = "",
    val postDate: String = "",
    val isEdited: Boolean = false,
    /*val imageProfile*/
)
