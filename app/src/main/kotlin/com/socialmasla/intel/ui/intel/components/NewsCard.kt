package com.socialmasla.intel.ui.intel.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.socialmasla.intel.data.model.NewsItem
import com.socialmasla.intel.ui.theme.MaslaBlack
import com.socialmasla.intel.ui.theme.MaslaWhite
import com.socialmasla.intel.analytics.RudderStackProvider
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

private fun formatDate(isoString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(isoString)
        val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
        date?.let { outputFormat.format(it) } ?: isoString
    } catch (e: Exception) {
        isoString
    }
}

@Composable
fun NewsCard(
    newsItem: NewsItem,
    progress: Float,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()

    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaslaWhite,
            contentColor = MaslaBlack
        ),
        border = BorderStroke(1.dp, MaslaBlack.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Progress bar at the top
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp),
                color = MaslaBlack,
                trackColor = MaslaBlack.copy(alpha = 0.05f)
            )

            // Main content
            Column(
                modifier = Modifier
                    .padding(28.dp)
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                // Top Row: Source + Date
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = newsItem.source.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaslaBlack.copy(alpha = 0.4f),
                        letterSpacing = 2.sp
                    )
                    
                    Text(
                        text = formatDate(newsItem.publishedAt).uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaslaBlack.copy(alpha = 0.3f),
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Article title
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaslaBlack,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp
                )

                if (newsItem.imageUrl.isNotBlank()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    AsyncImage(
                        model = newsItem.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Article summary
                Text(
                    text = newsItem.summary,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaslaBlack.copy(alpha = 0.7f),
                    lineHeight = 28.sp
                )
            }

            // Read More — bottom bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaslaBlack.copy(alpha = 0.05f))
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(
                    onClick = {
                        if (newsItem.url.isNotBlank()) {
                            // Track across Meta, Firebase, and RudderStack
                            com.socialmasla.intel.analytics.AnalyticsHelper.logReadMore(newsItem.url)
                            
                            RudderStackProvider.getAnalytics()?.track(
                                "read_more_clicked", 
                                buildJsonObject { 
                                    put("title", newsItem.title)
                                    put("source", newsItem.source)
                                }
                            )
                            uriHandler.openUri(newsItem.url)
                        }
                    }
                ) {
                    Text(
                        text = "READ MORE →",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaslaBlack,
                        letterSpacing = 1.5.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
