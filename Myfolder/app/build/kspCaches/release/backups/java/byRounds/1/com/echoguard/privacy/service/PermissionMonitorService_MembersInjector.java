package com.echoguard.privacy.service;

import com.echoguard.privacy.data.repository.MonitoringRepository;
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
public final class PermissionMonitorService_MembersInjector implements MembersInjector<PermissionMonitorService> {
  private final Provider<MonitoringRepository> monitoringRepositoryProvider;

  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public PermissionMonitorService_MembersInjector(
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<PermissionRepository> permissionRepositoryProvider) {
    this.monitoringRepositoryProvider = monitoringRepositoryProvider;
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  public static MembersInjector<PermissionMonitorService> create(
      Provider<MonitoringRepository> monitoringRepositoryProvider,
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new PermissionMonitorService_MembersInjector(monitoringRepositoryProvider, permissionRepositoryProvider);
  }

  @Override
  public void injectMembers(PermissionMonitorService instance) {
    injectMonitoringRepository(instance, monitoringRepositoryProvider.get());
    injectPermissionRepository(instance, permissionRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.echoguard.privacy.service.PermissionMonitorService.monitoringRepository")
  public static void injectMonitoringRepository(PermissionMonitorService instance,
      MonitoringRepository monitoringRepository) {
    instance.monitoringRepository = monitoringRepository;
  }

  @InjectedFieldSignature("com.echoguard.privacy.service.PermissionMonitorService.permissionRepository")
  public static void injectPermissionRepository(PermissionMonitorService instance,
      PermissionRepository permissionRepository) {
    instance.permissionRepository = permissionRepository;
  }
}
