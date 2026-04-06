package com.socialmasla.intel.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.socialmasla.intel.ui.navigation.Screen
import com.socialmasla.intel.ui.theme.MaslaBlack
import com.socialmasla.intel.ui.theme.MaslaWhite
import com.socialmasla.intel.ui.theme.MaslaCream
import com.socialmasla.intel.ui.theme.MaslaEmerald
import com.socialmasla.intel.ui.theme.MaslaCharcoal

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        Screen.Intel,
        Screen.Services
    )

    Column {
        androidx.compose.material3.HorizontalDivider(
            thickness = 1.dp,
            color = MaslaBlack.copy(alpha = 0.1f)
        )
        NavigationBar(
            containerColor = MaslaWhite,
            tonalElevation = 0.dp
        ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        
        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = if (isSelected) MaslaBlack else MaslaBlack.copy(alpha = 0.4f)
                    )
                },
                label = null,
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaslaBlack.copy(alpha = 0.05f),
                    selectedIconColor = MaslaBlack,
                    unselectedIconColor = MaslaBlack.copy(alpha = 0.4f)
                )
            )
        }
        }
    }
}
