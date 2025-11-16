package com.echoguard.privacy.data.model

/**
 * Simple data models for EchoGuard prototype
 * Without Room database annotations for simplicity
 */

data class AppInfo(
    val packageName: String,
    val appName: String,
    val versionName: String,
    val versionCode: Long,
    val isSystemApp: Boolean,
    val installTime: Long,
    val updateTime: Long,
    val targetSdkVersion: Int,
    val grantedPermissions: List<String>,
    val requestedPermissions: List<String>,
    val riskScore: Float
) {
    fun getRiskLevel(): RiskLevel = RiskLevel.fromScore(riskScore)
}

data class PermissionEvent(
    val id: String,
    val timestamp: Long,
    val packageName: String,
    val permission: String,
    val eventType: PermissionEventType,
    val details: String?,
    val riskLevel: RiskLevel
)

data class PrivacyAlert(
    val id: String,
    val timestamp: Long,
    val packageName: String,
    val appName: String,
    val alertType: AlertType,
    val title: String,
    val description: String,
    val riskLevel: RiskLevel,
    val isRead: Boolean,
    val isHandled: Boolean
)

data class UsageStats(
    val id: String,
    val packageName: String,
    val timestamp: Long,
    val usageDuration: Long,
    val foregroundTime: Long,
    val backgroundTime: Long,
    val dataUsage: Long
)

enum class RiskLevel(val displayName: String, val score: Int) {
    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3),
    CRITICAL("Critical", 4);

    companion object {
        fun fromScore(score: Float): RiskLevel {
            return when {
                score < 3.0f -> LOW
                score < 6.0f -> MEDIUM
                score < 8.5f -> HIGH
                else -> CRITICAL
            }
        }
    }
}

enum class PermissionEventType {
    GRANTED,
    REVOKED,
    REQUESTED,
    DENIED,
    USED
}

enum class AlertType(val displayName: String) {
    PERMISSION_GRANTED("Permission Granted"),
    PERMISSION_REVOKED("Permission Revoked"),
    SUSPICIOUS_ACTIVITY("Suspicious Activity"),
    DATA_ACCESS("Data Access"),
    BACKGROUND_ACTIVITY("Background Activity"),
    LOCATION_ACCESS("Location Access"),
    CAMERA_ACCESS("Camera Access"),
    MICROPHONE_ACCESS("Microphone Access"),
    CONTACTS_ACCESS("Contacts Access"),
    SMS_ACCESS("SMS Access"),
    STORAGE_ACCESS("Storage Access"),
    HIGH_RISK_APP("High Risk App Detected"),
    NEW_APP_INSTALLED("New App Installed")
}