package com.socialmasla.intel.ui.services.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.socialmasla.intel.data.model.ServiceItem
import com.socialmasla.intel.ui.theme.MaslaCreamPanel
import com.socialmasla.intel.ui.theme.MaslaBlack
import com.socialmasla.intel.ui.theme.MaslaWhite
import com.socialmasla.intel.ui.theme.MaslaEmerald
import com.socialmasla.intel.ui.theme.MaslaCharcoal
import com.socialmasla.intel.ui.theme.MaslaCream

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.IntrinsicSize

@Composable
fun ServiceCard(
    serviceItem: ServiceItem,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaslaWhite,
            contentColor = MaslaBlack
        ),
        border = BorderStroke(1.dp, MaslaBlack.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Icon(
                imageVector = serviceItem.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaslaBlack
            )
            
            Spacer(modifier = Modifier.size(12.dp))
            
            Text(
                text = serviceItem.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaslaBlack,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            
            Spacer(modifier = Modifier.size(6.dp))
            
            Text(
                text = serviceItem.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaslaBlack.copy(alpha = 0.8f),
                lineHeight = 22.sp,
                fontSize = 15.sp
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(serviceItem.url))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaslaBlack,
                    contentColor = MaslaWhite
                )
            ) {
                Text(
                    text = serviceItem.ctaText,
                    style = MaterialTheme.typography.labelLarge,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
