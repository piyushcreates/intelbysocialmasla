package com.socialmasla.intel.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceItem(
    val title: String,
    val description: String,
    val ctaText: String,
    val url: String,
    val icon: ImageVector
)
