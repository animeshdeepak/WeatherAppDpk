package com.example.weatherappdpk.data.screendata

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherappdpk.R

sealed class BottomNavItem(
    val route: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    @StringRes val labelResourceId: Int
) {
    data object Home : BottomNavItem("home", Icons.Filled.Home, Icons.Outlined.Home, R.string.home)
    data object Search :
        BottomNavItem("search", Icons.Filled.Search, Icons.Outlined.Search, R.string.search)

    data object Setting :
        BottomNavItem("profile", Icons.Filled.Settings, Icons.Outlined.Settings, R.string.setting)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Setting,
)