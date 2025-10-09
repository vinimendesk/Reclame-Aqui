package com.example.reclameaqui.data

import java.time.LocalDateTime

data class ComplaintPostUi(
    val id: String = "",
    val author: String = "",
    val text: String = "",
    val postDate: LocalDateTime = LocalDateTime.of(0, 0, 0, 0, 0),
    val isEdited: Boolean = false,
    /*val imageProfile*/
)
