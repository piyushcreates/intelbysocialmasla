package com.socialmasla.intel.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MaslaWhite,
    secondary = MaslaWhite,
    background = MaslaBlack,
    surface = MaslaBlack,
    onPrimary = MaslaBlack,
    onSecondary = MaslaBlack,
    onBackground = MaslaWhite,
    onSurface = MaslaWhite
)

private val LightColorScheme = lightColorScheme(
    primary = MaslaBlack,
    secondary = MaslaBlack,
    background = MaslaWhite,
    surface = MaslaWhite,
    onPrimary = MaslaWhite,
    onSecondary = MaslaWhite,
    onBackground = MaslaBlack,
    onSurface = MaslaBlack
)

@Composable
fun IntelBySocialMaslaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
