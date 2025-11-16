package com.echoguard.privacy.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Simple Settings Screen for prototype
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSettingsScreen() {
    var monitoringEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoScanEnabled by remember { mutableStateOf(false) }
    var biometricLockEnabled by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        // Privacy Monitoring Section
        SettingsSection(
            title = "Privacy Monitoring",
            icon = Icons.Default.Lock
        ) {
            SwitchSettingItem(
                title = "Enable Monitoring",
                subtitle = "Monitor app permissions and privacy events",
                checked = monitoringEnabled,
                onCheckedChange = { monitoringEnabled = it },
                icon = Icons.Default.CheckCircle
            )
            
            SwitchSettingItem(
                title = "Real-time Notifications",
                subtitle = "Get instant alerts for privacy violations",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it },
                icon = Icons.Default.Notifications,
                enabled = monitoringEnabled
            )
            
            SwitchSettingItem(
                title = "Auto Scan New Apps",
                subtitle = "Automatically check new app installations",
                checked = autoScanEnabled,
                onCheckedChange = { autoScanEnabled = it },
                icon = Icons.Default.Search,
                enabled = monitoringEnabled
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Security Section
        SettingsSection(
            title = "Security",
            icon = Icons.Default.Lock
        ) {
            SwitchSettingItem(
                title = "Biometric Lock",
                subtitle = "Require fingerprint to access app details",
                checked = biometricLockEnabled,
                onCheckedChange = { biometricLockEnabled = it },
                icon = Icons.Default.Lock
            )
            
            ClickableSettingItem(
                title = "Export Data",
                subtitle = "Export privacy reports and statistics",
                icon = Icons.Default.Share,
                onClick = { /* Handle export */ }
            )
            
            ClickableSettingItem(
                title = "Clear Cache",
                subtitle = "Clear temporary files and cached data",
                icon = Icons.Default.Delete,
                onClick = { /* Handle clear cache */ }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // App Info Section
        SettingsSection(
            title = "About",
            icon = Icons.Default.Info
        ) {
            ClickableSettingItem(
                title = "Privacy Policy",
                subtitle = "View our privacy policy",
                icon = Icons.Default.Info,
                onClick = { /* Handle privacy policy */ }
            )
            
            ClickableSettingItem(
                title = "Version",
                subtitle = "EchoGuard v1.0.0 (Prototype)",
                icon = Icons.Default.Settings,
                onClick = { /* Handle version info */ }
            )
            
            ClickableSettingItem(
                title = "Help & Support",
                subtitle = "Get help or contact support",
                icon = Icons.Default.Info,
                onClick = { /* Handle help */ }
            )
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
        
        // Section Content
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun SwitchSettingItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: ImageVector,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { 
                onCheckedChange(!checked) 
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) MaterialTheme.colorScheme.onSurface 
                   else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (enabled) MaterialTheme.colorScheme.onSurface 
                        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = if (enabled) MaterialTheme.colorScheme.onSurfaceVariant 
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
            )
        }
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
    }
}

@Composable
fun ClickableSettingItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}