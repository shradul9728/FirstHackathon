package com.echoguard.privacy.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.echoguard.privacy.data.model.*

/**
 * Simple Dashboard Screen for prototype
 * Shows basic privacy statistics and recent alerts
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Privacy Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Stats Cards Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Apps Monitored",
                value = "24",
                subtitle = "3 high risk",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            
            StatCard(
                title = "Active Alerts",
                value = "8",
                subtitle = "2 critical",
                color = Color(0xFFFF5722),
                modifier = Modifier.weight(1f)
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Privacy Score",
                value = "72",
                subtitle = "Good protection",
                color = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
            
            StatCard(
                title = "Scans Today",
                value = "15",
                subtitle = "Last: 2h ago",
                color = Color(0xFF9C27B0),
                modifier = Modifier.weight(1f)
            )
        }
        
        // Recent Alerts Section
        Text(
            text = "Recent Alerts",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        // Recent Alerts List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getRecentAlerts()) { alert ->
                RecentAlertItem(alert = alert)
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    subtitle: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
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
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Text(
                        text = value,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                    
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when (title) {
                            "Apps Monitored" -> Icons.Default.List
                            "Active Alerts" -> Icons.Default.Notifications
                            "Privacy Score" -> Icons.Default.Lock
                            else -> Icons.Default.Star
                        },
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecentAlertItem(alert: PrivacyAlert) {
    val riskColor = when (alert.riskLevel) {
        RiskLevel.CRITICAL -> Color.Red
        RiskLevel.HIGH -> Color(0xFFFF5722)
        RiskLevel.MEDIUM -> Color(0xFFFF9800)
        RiskLevel.LOW -> Color.Green
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to alert details */ },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Alert Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(riskColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
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
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Alert Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = alert.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = alert.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
            
            // Time
            Text(
                text = formatTimeAgo(alert.timestamp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    return when {
        diff < 60 * 1000 -> "Now"
        diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)}m"
        diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)}h"
        else -> "${diff / (24 * 60 * 60 * 1000)}d"
    }
}

// Sample data for prototype
private fun getRecentAlerts(): List<PrivacyAlert> {
    return listOf(
        PrivacyAlert(
            id = "1",
            timestamp = System.currentTimeMillis() - 3600000,
            packageName = "com.whatsapp",
            appName = "WhatsApp",
            alertType = AlertType.CAMERA_ACCESS,
            title = "Camera Access",
            description = "WhatsApp accessed camera 15 times today",
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
            title = "Location Tracking",
            description = "Google Maps tracking location in background",
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
            title = "New App Installed",
            description = "TikTok installed with high-risk permissions",
            riskLevel = RiskLevel.CRITICAL,
            isRead = false,
            isHandled = false
        )
    )
}