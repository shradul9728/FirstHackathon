# EchoGuard Privacy Policy

**Effective Date:** November 15, 2025  
**Last Updated:** November 15, 2025

## Introduction

EchoGuard ("we," "our," or "us") is committed to protecting your privacy. This Privacy Policy explains how our Android application ("EchoGuard" or "the App") handles information when you use our privacy monitoring service.

## Core Privacy Principles

### 🔒 **Zero Data Collection**
- EchoGuard does not collect, transmit, or share any personal data
- All processing occurs locally on your device
- No analytics, telemetry, or usage tracking
- No user accounts or cloud services required

### 📱 **Local-Only Processing**
- Permission usage logs are stored only on your device
- Privacy scores and risk assessments are calculated locally
- App analysis happens entirely on-device
- No internet connection required for core functionality

## Information We Process (Locally Only)

### **App Information**
- **What we process:** Names, package names, and permission lists of installed apps
- **How we use it:** To calculate privacy risk scores and generate recommendations
- **Storage:** Encrypted locally using Android Keystore

### **Permission Usage Logs**
- **What we process:** Timestamps and types of permission access by apps
- **How we use it:** To detect unusual patterns and generate privacy alerts
- **Storage:** Encrypted locally with configurable retention periods (default: 30 days)

### **User Settings**
- **What we process:** Your monitoring preferences and app configuration
- **How we use it:** To customize monitoring behavior and alert frequency
- **Storage:** Encrypted locally using Android DataStore

## Data Security

### **Encryption**
- All sensitive data is encrypted using Android Keystore system
- Encryption keys are generated and stored in hardware security module when available
- Data is protected both at rest and during processing

### **Access Control**
- Only EchoGuard has access to its encrypted data
- No other apps can access EchoGuard's stored information
- User can delete all data at any time through app settings

## Permissions We Require

### **Special Permissions**
1. **Usage Access (`PACKAGE_USAGE_STATS`)**
   - **Purpose:** Monitor when apps use permissions
   - **Justification:** Essential for real-time privacy monitoring
   - **User Control:** Can be revoked anytime in Android Settings

2. **Notification Listener (`BIND_NOTIFICATION_LISTENER_SERVICE`)**
   - **Purpose:** Detect permission usage from system notifications
   - **Justification:** Provides additional monitoring accuracy
   - **User Control:** Can be revoked anytime in Android Settings

### **Standard Permissions**
- **Query All Packages:** To analyze privacy risks of all installed apps
- **Post Notifications:** To send privacy alerts to user
- **Boot Receiver:** To restart monitoring after device reboot
- **Foreground Service:** To maintain monitoring when app is not active

## Your Rights and Controls

### **Data Management**
- **View Data:** Access all stored permission logs and settings
- **Export Data:** Export your privacy data in readable format
- **Delete Data:** Permanently delete all stored information
- **Retention Control:** Set custom data retention periods (7-365 days)

### **Monitoring Control**
- **Toggle Monitoring:** Enable/disable real-time monitoring
- **Alert Preferences:** Customize alert frequency and types
- **Privacy Levels:** Choose from Minimal, Balanced, Strict, or Paranoid monitoring

### **Permission Control**
- **Revoke Access:** Remove Usage Access or Notification Listener permissions
- **Selective Monitoring:** Choose which types of permission access to monitor

## Data Retention

### **Automatic Deletion**
- Permission logs are automatically deleted after retention period expires
- Default retention: 30 days (configurable: 7-365 days)
- Privacy scores older than 90 days are automatically removed

### **Manual Deletion**
- Users can delete all data immediately through Settings
- Uninstalling EchoGuard removes all associated data
- No data recovery possible after deletion

## Third-Party Services

### **No Third-Party Integration**
- EchoGuard does not integrate with any external services
- No advertising networks or analytics platforms
- No crash reporting or error tracking services
- No cloud storage or backup services

## Changes to Apps We Monitor

### **When Apps are Installed/Removed**
- EchoGuard automatically detects new app installations
- Permission analysis is performed locally for new apps
- Data for uninstalled apps is automatically cleaned up
- No information about your app usage is transmitted externally

## Children's Privacy

### **Age Restrictions**
- EchoGuard does not knowingly collect information from children under 13
- Parental supervision recommended for users under 18
- No age verification or personal information collection

## International Users

### **Global Compatibility**
- EchoGuard works identically worldwide
- No data crosses international borders (all local processing)
- Complies with GDPR, CCPA, and other privacy regulations through zero-data-collection approach

## Security Measures

### **Technical Safeguards**
- Industry-standard encryption (AES-256)
- Secure key management via Android Keystore
- No network communication for sensitive data
- Regular security updates

### **Operational Safeguards**
- Minimal data collection by design
- Automated data deletion
- No human access to user data
- Open-source components for transparency

## Compliance

### **Regulatory Compliance**
- **GDPR:** Compliant through data minimization and local processing
- **CCPA:** No personal information sale or sharing
- **Google Play Policy:** Adheres to all Play Store privacy requirements
- **Android Security:** Follows Android security best practices

## Contact Information

### **Privacy Questions**
- **Email:** privacy@echoguard.app
- **Response Time:** Within 72 hours
- **Languages:** English

### **Security Issues**
- **Email:** security@echoguard.app
- **Response Time:** Within 24 hours for critical issues

## Updates to This Policy

### **Notification of Changes**
- Users will be notified in-app of any material changes
- Updated policy will be available in app settings
- Continued use constitutes acceptance of changes

### **Version History**
- Version 1.0: Initial release (November 15, 2025)

## Technical Implementation Details

### **Data Processing Methods**
```
1. App Scanning:
   - PackageManager API queries (local only)
   - Permission analysis algorithms (on-device)
   - Risk scoring calculations (local processing)

2. Permission Monitoring:
   - UsageStatsManager integration (system service)
   - NotificationListenerService monitoring (local processing)
   - Real-time event detection (on-device analysis)

3. Data Storage:
   - Room Database with encryption (local SQLite)
   - DataStore for preferences (encrypted preferences)
   - Android Keystore for key management (hardware security)
```

### **Privacy-by-Design Implementation**
- **Data Minimization:** Only necessary data is processed
- **Purpose Limitation:** Data used only for stated privacy monitoring purposes
- **Storage Limitation:** Automatic deletion after retention period
- **Transparency:** Full disclosure of all data processing activities
- **User Control:** Complete user control over data and settings

---

**This privacy policy reflects our commitment to protecting your privacy through technical design rather than just policies. By processing everything locally and collecting no data, we eliminate privacy risks rather than just managing them.**

**Last Updated:** November 15, 2025  
**EchoGuard Privacy Team**