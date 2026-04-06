package com.socialmasla.intel.ui.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import com.socialmasla.intel.analytics.RudderStackProvider
import com.socialmasla.intel.ui.theme.MaslaBlack
import com.socialmasla.intel.ui.theme.MaslaWhite

@Composable
fun ServicesScreen() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    
    val services = listOf(
        Triple("Performance Marketing", Icons.Default.TrendingUp, "High-scale Google & Meta ad campaigns optimized for ROAS."),
        Triple("AI Automations", Icons.Default.AutoMode, "Custom-built AI systems to automate workflows and ad reporting."),
        Triple("WhatsApp API (Pulse)", Icons.Default.Chat, "The ultimate conversion tool for direct-to-consumer engagement."),
        Triple("Landing Pages", Icons.Default.Dashboard, "High-converting editorial pages designed for maximum performance."),
        Triple("AI Chatbot", Icons.Default.SmartToy, "24/7 intelligent sales agents for instant customer conversion."),
        Triple("Learn", Icons.Default.School, "Join the elite performance marketing masterclass to scale your brand.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaslaWhite)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(top = 32.dp, bottom = 24.dp)) {
            Text(
                text = "SERVICES",
                style = MaterialTheme.typography.labelSmall,
                color = MaslaBlack,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Social Masla is an AI-first marketing agency dedicated to scaling brands through intelligent automation and high-performance advertising. We bridge the gap between technical infrastructure and creative growth.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaslaBlack.copy(alpha = 0.9f),
                lineHeight = 28.sp,
                fontWeight = FontWeight.Medium
            )
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaslaWhite),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                services.forEach { (title, icon, brief) ->
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaslaBlack,
                            modifier = Modifier.size(20.dp).padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaslaBlack,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = brief,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaslaBlack.copy(alpha = 0.6f),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // Track conversion event using Kotlin SDK syntax via provider
                        RudderStackProvider.getAnalytics()?.track(
                            "learn_more_clicked", 
                            buildJsonObject { put("action", "explore_services") }
                        )
                        
                        uriHandler.openUri("https://socialmasla.com/services/")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaslaBlack),
                    shape = MaterialTheme.shapes.extraSmall,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "LEARN MORE",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaslaWhite,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}
