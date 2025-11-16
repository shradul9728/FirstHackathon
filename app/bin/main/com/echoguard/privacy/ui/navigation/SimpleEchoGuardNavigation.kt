package com.echoguard.privacy.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.echoguard.privacy.ui.dashboard.SimpleDashboardScreen
import com.echoguard.privacy.ui.apps.SimpleAppsScreen
import com.echoguard.privacy.ui.alerts.AlertsScreen
import com.echoguard.privacy.ui.settings.SimpleSettingsScreen

/**
 * Simple Navigation for prototype without dependency injection
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleEchoGuardNavigation() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                SimpleDashboardScreen()
            }
            
            composable(Screen.Apps.route) {
                SimpleAppsScreen()
            }
            
            composable(Screen.Alerts.route) {
                AlertsScreen()
            }
            
            composable(Screen.Settings.route) {
                SimpleSettingsScreen()
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Home)
    object Apps : Screen("apps", "Apps", Icons.Default.List)
    object Alerts : Screen("alerts", "Alerts", Icons.Default.Notifications)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}

private val bottomNavItems = listOf(
    Screen.Dashboard,
    Screen.Apps,
    Screen.Alerts,
    Screen.Settings
)