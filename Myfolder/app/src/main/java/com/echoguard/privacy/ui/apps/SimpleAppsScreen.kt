package com.echoguard.privacy.ui.apps

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
 * Simple Apps Screen for prototype
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAppsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val apps = getSampleApps()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Installed Apps",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search apps") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear search")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        
        // Stats Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatChip("All (${apps.size})", true, Modifier.weight(1f))
            StatChip("High Risk (${apps.count { it.riskScore >= 8 }})", false, Modifier.weight(1f))
            StatChip("System (${apps.count { it.isSystemApp }})", false, Modifier.weight(1f))
        }
        
        // Apps List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apps.filter { 
                searchQuery.isEmpty() || 
                it.appName.contains(searchQuery, ignoreCase = true) ||
                it.packageName.contains(searchQuery, ignoreCase = true)
            }) { app ->
                AppListItem(
                    app = app,
                    onClick = { /* Navigate to app detail */ }
                )
            }
        }
    }
}

@Composable
fun StatChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    FilterChip(
        onClick = { },
        label = { Text(text) },
        selected = isSelected,
        modifier = modifier
    )
}

@Composable
fun AppListItem(
    app: AppInfo,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Icon (placeholder)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = app.appName.take(1).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // App Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = app.appName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = "${app.grantedPermissions.size} permissions • ${
                        if (app.isSystemApp) "System" else "User"
                    }",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Risk Score
            RiskScoreBadge(riskScore = app.riskScore)
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View details",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun RiskScoreBadge(riskScore: Float) {
    val riskLevel = RiskLevel.fromScore(riskScore)
    val color = when (riskLevel) {
        RiskLevel.LOW -> Color.Green
        RiskLevel.MEDIUM -> Color(0xFFFF9800)
        RiskLevel.HIGH -> Color(0xFFFF5722)
        RiskLevel.CRITICAL -> Color.Red
    }
    
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = riskScore.toInt().toString(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

// Sample data for prototype
private fun getSampleApps(): List<AppInfo> {
    return listOf(
        AppInfo(
            packageName = "com.whatsapp",
            appName = "WhatsApp",
            versionName = "2.23.24.26",
            versionCode = 232424260,
            isSystemApp = false,
            installTime = System.currentTimeMillis() - 86400000 * 30,
            updateTime = System.currentTimeMillis() - 86400000 * 7,
            targetSdkVersion = 33,
            grantedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.READ_CONTACTS",
                "android.permission.ACCESS_FINE_LOCATION"
            ),
            requestedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.READ_CONTACTS",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.INTERNET"
            ),
            riskScore = 6.5f
        ),
        AppInfo(
            packageName = "com.instagram.android",
            appName = "Instagram",
            versionName = "304.0.0.21.107",
            versionCode = 304000021,
            isSystemApp = false,
            installTime = System.currentTimeMillis() - 86400000 * 60,
            updateTime = System.currentTimeMillis() - 86400000 * 3,
            targetSdkVersion = 33,
            grantedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.READ_CONTACTS",
                "android.permission.READ_PHONE_STATE"
            ),
            requestedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.READ_CONTACTS",
                "android.permission.READ_PHONE_STATE",
                "android.permission.INTERNET"
            ),
            riskScore = 8.2f
        ),
        AppInfo(
            packageName = "com.android.systemui",
            appName = "System UI",
            versionName = "14",
            versionCode = 34,
            isSystemApp = true,
            installTime = System.currentTimeMillis() - 86400000 * 365,
            updateTime = System.currentTimeMillis() - 86400000 * 30,
            targetSdkVersion = 34,
            grantedPermissions = listOf(
                "android.permission.SYSTEM_ALERT_WINDOW",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.CAMERA"
            ),
            requestedPermissions = listOf(
                "android.permission.SYSTEM_ALERT_WINDOW",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.CAMERA"
            ),
            riskScore = 2.1f
        ),
        AppInfo(
            packageName = "com.tiktok",
            appName = "TikTok",
            versionName = "32.5.4",
            versionCode = 2023205040,
            isSystemApp = false,
            installTime = System.currentTimeMillis() - 86400000 * 5,
            updateTime = System.currentTimeMillis() - 86400000 * 1,
            targetSdkVersion = 33,
            grantedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.READ_CONTACTS",
                "android.permission.READ_PHONE_STATE",
                "android.permission.READ_SMS"
            ),
            requestedPermissions = listOf(
                "android.permission.CAMERA",
                "android.permission.RECORD_AUDIO", 
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.READ_CONTACTS",
                "android.permission.READ_PHONE_STATE",
                "android.permission.READ_SMS",
                "android.permission.INTERNET"
            ),
            riskScore = 9.1f
        )
    )
}