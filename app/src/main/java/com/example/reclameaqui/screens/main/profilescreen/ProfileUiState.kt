package com.example.reclameaqui.screens.main.profilescreen

import com.example.reclameaqui.data.User

data class ProfileUiState(
    val userProfile: User = User(),
    val openSingOutDialog: Boolean = false
)