package com.example.reclameaqui.screens.main.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.reclameaqui.R
import com.example.reclameaqui.navigation.ScreenType

data class NavigationItemContent(
    val screenType: ScreenType,
    val icon: ImageVector,
    val text: String
)

object NavigationItemContentList {
    @Composable
    fun getNavigationContentList(): List<NavigationItemContent> {
        return listOf(
            NavigationItemContent(
                screenType = ScreenType.RECENTCOMPLAINTS,
                icon = Icons.AutoMirrored.Filled.Chat,
                text = ScreenType.RECENTCOMPLAINTS.screen
            ),
            NavigationItemContent(
                screenType = ScreenType.FAMILYMEMBERS,
                icon = Icons.Default.Groups,
                text = ScreenType.FAMILYMEMBERS.screen
            ),
            NavigationItemContent(
                screenType = ScreenType.MAKEACOMPLAINT,
                icon = Icons.Default.Edit,
                text = ScreenType.RECENTCOMPLAINTS.screen
            ),
            NavigationItemContent(
                screenType = ScreenType.PROFILE,
                icon = Icons.Default.PersonPin,
                text = ScreenType.RECENTCOMPLAINTS.screen
            )
        )
    }
}
