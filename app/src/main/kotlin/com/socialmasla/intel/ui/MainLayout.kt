package com.socialmasla.intel.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.socialmasla.intel.ui.components.BottomNavBar
import com.socialmasla.intel.ui.navigation.Screen
import com.socialmasla.intel.ui.intel.IntelScreen
import com.socialmasla.intel.ui.services.ServicesScreen

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Intel.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(400, easing = EaseOutQuart)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(400, easing = EaseOutQuart)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(400, easing = EaseOutQuart)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(400, easing = EaseOutQuart)
                )
            }
        ) {
            composable(Screen.Intel.route) {
                IntelScreen()
            }
            composable(Screen.Services.route) {
                ServicesScreen()
            }
        }
    }
}
