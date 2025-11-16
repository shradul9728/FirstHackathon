package com.echoguard.privacy.ui.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.echoguard.privacy.data.model.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Alerts screen showing privacy alerts and security notifications
 */
@Composable
fun AlertsScreen() {
    // Placeholder alerts for demonstration
    val sampleAlerts = listOf(
        PrivacyAlert(
            id = "1",
            timestamp = System.currentTimeMillis() - 3600000,
            packageName = "com.whatsapp",
            appName = "WhatsApp",
            alertType = AlertType.CAMERA_ACCESS,
            title = "High-Risk App Detected",
            description = "WhatsApp has accessed your camera 15 times in the last hour",
            riskLevel = RiskLevel.HIGH,
            isRead = false,
            isHandled = false
        ),
        PrivacyAlert(
            id = "2",
            timestamp = System.currentTimeMillis() - 7200000,
            packageName = "com.google.android.apps.maps",
            appName = "Google Maps",
            alertType = AlertType.LOCATION_ACCESS,
            title = "Background Location Access",
            description = "Google Maps is tracking your location in the background",
            riskLevel = RiskLevel.MEDIUM,
            isRead = false,
            isHandled = false
        ),
        PrivacyAlert(
            id = "3",
            timestamp = System.currentTimeMillis() - 86400000,
            packageName = "com.zhiliaoapp.musically",
            appName = "TikTok",
            alertType = AlertType.HIGH_RISK_APP,
            title = "New High-Risk App Installed",
            description = "TikTok was recently installed with sensitive permissions",
            riskLevel = RiskLevel.CRITICAL,
            isRead = false,
            isHandled = false
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Privacy Alerts",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${sampleAlerts.size} active alerts",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Alerts List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleAlerts) { alert ->
                AlertCard(alert = alert)
            }
        }
    }
}

/**
 * Individual alert card component
 */
@Composable
fun AlertCard(alert: PrivacyAlert) {
    val riskColor = when (alert.riskLevel) {
        RiskLevel.CRITICAL -> Color.Red
        RiskLevel.HIGH -> Color(0xFFFF5722)
        RiskLevel.MEDIUM -> Color(0xFFFF9800)
        RiskLevel.LOW -> Color.Green
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = riskColor.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = when (alert.riskLevel) {
                            RiskLevel.CRITICAL -> Icons.Default.Delete
                            RiskLevel.HIGH -> Icons.Default.Warning
                            RiskLevel.MEDIUM -> Icons.Default.Info
                            RiskLevel.LOW -> Icons.Default.Notifications
                        },
                        contentDescription = null,
                        tint = riskColor,
                        modifier = Modifier.size(24.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Column {
                        Text(
                            text = alert.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        
                        Text(
                            text = formatTimestamp(alert.timestamp),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Badge(
                    containerColor = riskColor
                ) {
                    Text(
                        text = alert.riskLevel.name,
                        color = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = alert.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "App: ${alert.appName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* Dismiss alert */ }) {
                    Text("Dismiss")
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Button(
                    onClick = { /* View details */ }
                ) {
                    Text("View Details")
                }
            }
        }
    }
}

/**
 * Formats timestamp to readable string
 */
fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    return when {
        diff < 60 * 1000 -> "Just now"
        diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)} minutes ago"
        diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)} hours ago"
        else -> {
            val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}