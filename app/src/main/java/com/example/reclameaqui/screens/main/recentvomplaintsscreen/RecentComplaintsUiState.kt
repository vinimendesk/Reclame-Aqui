package com.example.reclameaqui.screens.main.recentvomplaintsscreen

import com.example.reclameaqui.data.ComplaintPost
import com.example.reclameaqui.data.ComplaintPostUi
import com.example.reclameaqui.data.User

data class RecentComplaintsUiState(
    val complaintsList: List<ComplaintPostUi> = listOf<ComplaintPostUi>()
)
