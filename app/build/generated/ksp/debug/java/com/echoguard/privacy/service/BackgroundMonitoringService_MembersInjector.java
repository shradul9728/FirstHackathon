package com.echoguard.privacy.service;

import com.echoguard.privacy.data.repository.PermissionRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class BackgroundMonitoringService_MembersInjector implements MembersInjector<BackgroundMonitoringService> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  private final Provider<UsageMonitoringService> usageMonitoringServiceProvider;

  public BackgroundMonitoringService_MembersInjector(
      Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<UsageMonitoringService> usageMonitoringServiceProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
    this.usageMonitoringServiceProvider = usageMonitoringServiceProvider;
  }

  public static MembersInjector<BackgroundMonitoringService> create(
      Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<UsageMonitoringService> usageMonitoringServiceProvider) {
    return new BackgroundMonitoringService_MembersInjector(permissionRepositoryProvider, usageMonitoringServiceProvider);
  }

  @Override
  public void injectMembers(BackgroundMonitoringService instance) {
    injectPermissionRepository(instance, permissionRepositoryProvider.get());
    injectUsageMonitoringService(instance, usageMonitoringServiceProvider.get());
  }

  @InjectedFieldSignature("com.echoguard.privacy.service.BackgroundMonitoringService.permissionRepository")
  public static void injectPermissionRepository(BackgroundMonitoringService instance,
      PermissionRepository permissionRepository) {
    instance.permissionRepository = permissionRepository;
  }

  @InjectedFieldSignature("com.echoguard.privacy.service.BackgroundMonitoringService.usageMonitoringService")
  public static void injectUsageMonitoringService(BackgroundMonitoringService instance,
      UsageMonitoringService usageMonitoringService) {
    instance.usageMonitoringService = usageMonitoringService;
  }
}
