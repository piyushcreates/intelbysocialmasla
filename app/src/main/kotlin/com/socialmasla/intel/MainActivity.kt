package com.socialmasla.intel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.socialmasla.intel.analytics.RudderStackProvider
import com.socialmasla.intel.ui.theme.IntelBySocialMaslaTheme
import com.socialmasla.intel.ui.MainLayout

import androidx.compose.runtime.*
import com.socialmasla.intel.ui.components.CustomSplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize RudderStack Kotlin SDK via Provider
        RudderStackProvider.initialize(
            context = this.application,
            writeKey = BuildConfig.RUDDER_WRITE_KEY,
            dataPlaneUrl = BuildConfig.RUDDER_DATA_PLANE_URL
        )

        enableEdgeToEdge()
        setContent {
            IntelBySocialMaslaTheme {
                var showSplash by remember { mutableStateOf(true) }
                
                LaunchedEffect(Unit) {
                    delay(1500) // Show BIG logo for 1.5 seconds
                    showSplash = false
                }

                if (showSplash) {
                    CustomSplashScreen()
                } else {
                    MainLayout()
                }
            }
        }
    }
}
