package com.echoguard.privacy.service;

import android.content.Context;
import com.echoguard.privacy.data.repository.MonitoringRepository;
import com.echoguard.privacy.data.repository.PermissionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UsageMonitoringService_Factory implements Factory<UsageMonitoringService> {
  private final Provider<Context> contextProvider;

  private final Provider<MonitoringRepository> monitoringRepositoryProvider;

  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public UsageMonitoringService_Factory(Provider<Context> contextProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<PermissionRepository> permissionRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.monitoringRepositoryProvider = monitoringRepositoryProvider;
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  @Override
  public UsageMonitoringService get() {
    return newInstance(contextProvider.get(), monitoringRepositoryProvider.get(), permissionRepositoryProvider.get());
  }

  public static UsageMonitoringService_Factory create(Provider<Context> contextProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new UsageMonitoringService_Factory(contextProvider, monitoringRepositoryProvider, permissionRepositoryProvider);
  }

  public static UsageMonitoringService newInstance(Context context,
      MonitoringRepository monitoringRepository, PermissionRepository permissionRepository) {
    return new UsageMonitoringService(context, monitoringRepository, permissionRepository);
  }
}
