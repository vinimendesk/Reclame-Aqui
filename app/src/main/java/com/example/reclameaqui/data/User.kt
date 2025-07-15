package com.example.reclameaqui.data

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val whatLikeMore: String = "",
    val whatDislikeMore: String = "",
    val complaintsCount: Int = 0
)
