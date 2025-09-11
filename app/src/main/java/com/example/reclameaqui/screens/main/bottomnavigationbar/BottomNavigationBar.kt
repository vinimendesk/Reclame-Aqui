package com.example.reclameaqui.screens.main.bottomnavigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reclameaqui.navigation.ScreenType
import com.example.reclameaqui.ui.theme.AzulFracoBackground
import com.example.reclameaqui.ui.theme.CinzaFracoIcon
import com.example.reclameaqui.ui.theme.RosaBackground


@Composable
fun BottomNavigationBar(
    onTabPressed: (String) -> Unit,
    currentScreen: ScreenType,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier,
) {

    NavigationBar(
        modifier = modifier,
        containerColor = if (currentScreen == ScreenType.MAKEACOMPLAINT) {
            RosaBackground
        } else { AzulFracoBackground }
    ) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentScreen == navItem.screenType,
                onClick = { onTabPressed(navItem.screenType.name) },
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = Color.Transparent
                            )
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = navItem.icon,
                            tint = if (currentScreen == navItem.screenType) Color.Black
                                    else CinzaFracoIcon,
                            contentDescription = navItem.screenType.screen,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(35.dp)
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.Black,
                    unselectedIconColor = CinzaFracoIcon
                )
            )
        }
    }

}