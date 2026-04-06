package com.socialmasla.intel.ui.intel

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.ContentTransform
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.socialmasla.intel.ui.theme.MaslaBlack
import com.socialmasla.intel.ui.theme.MaslaWhite
import com.socialmasla.intel.ui.intel.components.NewsCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun IntelScreen(
    viewModel: IntelViewModel = viewModel()
) {
    val context = LocalContext.current
    val newsItems by viewModel.newsItems.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()
    val progress by viewModel.progress.collectAsState()
    val isSwipingForward by viewModel.isSwipingForward.collectAsState()

    // Onboarding state logic
    val sharedPrefs = remember { context.getSharedPreferences("intel_prefs", Context.MODE_PRIVATE) }
    var showOnboarding by remember { mutableStateOf(!sharedPrefs.getBoolean("has_seen_onboarding_v2", false)) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaslaWhite)
        ) {
            // Header: Title
            Text(
                text = "INTEL",
                style = MaterialTheme.typography.labelSmall,
                color = MaslaBlack,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)
            )
            
            // Navigation Row: Arrows (Above Card, Below Title)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 12.dp), // Space before card
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { viewModel.swipePrevious() },
                    tint = MaslaBlack.copy(alpha = 0.4f)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { viewModel.swipeNext() },
                    tint = MaslaBlack.copy(alpha = 0.4f)
                )
            }

            if (newsItems.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                        .pointerInput(Unit) {
                            var isSwiping = false
                            detectHorizontalDragGestures(
                                onDragStart = { isSwiping = false },
                                onDragEnd = { isSwiping = false },
                                onDragCancel = { isSwiping = false },
                                onHorizontalDrag = { change, dragAmount ->
                                    change.consume()
                                    if (!isSwiping) {
                                        if (dragAmount < -30) { // Swipe Left (Next)
                                            isSwiping = true
                                            viewModel.swipeNext()
                                        } else if (dragAmount > 30) { // Swipe Right (Previous)
                                            isSwiping = true
                                            viewModel.swipePrevious()
                                        }
                                    }
                                }
                            )
                        }
                ) {
                    if (currentIndex < newsItems.size) {
                        AnimatedContent(
                            targetState = newsItems[currentIndex],
                            transitionSpec = {
                                val direction = if (isSwipingForward) 1 else -1
                                ContentTransform(
                                    targetContentEnter = slideInHorizontally(animationSpec = tween(300)) { width -> direction * width } + fadeIn(),
                                    initialContentExit = slideOutHorizontally(animationSpec = tween(300)) { width -> -direction * width } + fadeOut()
                                )
                            },
                            label = "IntelCardTransition"
                        ) { newsItem ->
                            NewsCard(
                                newsItem = newsItem,
                                progress = progress
                            )
                        }
                    }
                }
            } else {
                LoadingScreen()
            }
        }

        // Onboarding Overlay (Dual Arrow Animation)
        if (showOnboarding && newsItems.isNotEmpty()) {
            val infiniteTransition = rememberInfiniteTransition(label = "SwipeAnimation")
            val arrowOffset by infiniteTransition.animateFloat(
                initialValue = 60f,
                targetValue = -60f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1200),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "ArrowOffset"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f))
                    .clickable { 
                        showOnboarding = false
                        sharedPrefs.edit().putBoolean("has_seen_onboarding_v2", true).apply()
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.height(140.dp), contentAlignment = Alignment.Center) {
                        // Left Arrow animating
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .offset(x = (-40).dp + arrowOffset.dp),
                            tint = Color.White.copy(alpha = 0.8f)
                        )
                        // Right Arrow static/mirroring for bi-directional hint
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .offset(x = 40.dp - arrowOffset.dp),
                            tint = Color.White.copy(alpha = 0.8f)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "EXPLORE TO DISCOVER",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaslaWhite,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Swipe in either direction to browse",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaslaWhite.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    Text(
                        text = "TAP TO BEGIN",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaslaWhite.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize().background(MaslaWhite),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaslaBlack,
            strokeWidth = 3.dp
        )
    }
}
