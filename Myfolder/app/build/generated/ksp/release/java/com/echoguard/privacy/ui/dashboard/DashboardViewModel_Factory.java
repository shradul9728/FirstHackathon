package com.echoguard.privacy.ui.dashboard;

import com.echoguard.privacy.data.preferences.SettingsManager;
import com.echoguard.privacy.data.repository.MonitoringRepository;
import com.echoguard.privacy.data.repository.PermissionRepository;
import com.echoguard.privacy.service.UsageMonitoringService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  private final Provider<MonitoringRepository> monitoringRepositoryProvider;

  private final Provider<SettingsManager> settingsManagerProvider;

  private final Provider<UsageMonitoringService> usageMonitoringServiceProvider;

  public DashboardViewModel_Factory(Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<SettingsManager> settingsManagerProvider,
      Provider<UsageMonitoringService> usageMonitoringServiceProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
    this.monitoringRepositoryProvider = monitoringRepositoryProvider;
    this.settingsManagerProvider = settingsManagerProvider;
    this.usageMonitoringServiceProvider = usageMonitoringServiceProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(permissionRepositoryProvider.get(), monitoringRepositoryProvider.get(), settingsManagerProvider.get(), usageMonitoringServiceProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<SettingsManager> settingsManagerProvider,
      Provider<UsageMonitoringService> usageMonitoringServiceProvider) {
    return new DashboardViewModel_Factory(permissionRepositoryProvider, monitoringRepositoryProvider, settingsManagerProvider, usageMonitoringServiceProvider);
  }

  public static DashboardViewModel newInstance(PermissionRepository permissionRepository,
      MonitoringRepository monitoringRepository, SettingsManager settingsManager,
      UsageMonitoringService usageMonitoringService) {
    return new DashboardViewModel(permissionRepository, monitoringRepository, settingsManager, usageMonitoringService);
  }
}
