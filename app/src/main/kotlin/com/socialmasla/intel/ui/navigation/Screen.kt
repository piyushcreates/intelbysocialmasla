package com.socialmasla.intel.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.GridView
import androidx.compose.ui.graphics.vector.ImageVector
import com.socialmasla.intel.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Intel : Screen("intel", R.string.intel_tab, Icons.Filled.Radar)
    object Services : Screen("services", R.string.services_tab, Icons.Filled.GridView)
}
