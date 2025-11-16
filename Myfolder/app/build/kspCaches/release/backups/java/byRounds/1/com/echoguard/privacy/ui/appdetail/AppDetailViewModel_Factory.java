package com.echoguard.privacy.ui.appdetail;

import com.echoguard.privacy.data.repository.MonitoringRepository;
import com.echoguard.privacy.data.repository.PermissionRepository;
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
public final class AppDetailViewModel_Factory implements Factory<AppDetailViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  private final Provider<MonitoringRepository> monitoringRepositoryProvider;

  public AppDetailViewModel_Factory(Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
    this.monitoringRepositoryProvider = monitoringRepositoryProvider;
  }

  @Override
  public AppDetailViewModel get() {
    return newInstance(permissionRepositoryProvider.get(), monitoringRepositoryProvider.get());
  }

  public static AppDetailViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<MonitoringRepository> monitoringRepositoryProvider) {
    return new AppDetailViewModel_Factory(permissionRepositoryProvider, monitoringRepositoryProvider);
  }

  public static AppDetailViewModel newInstance(PermissionRepository permissionRepository,
      MonitoringRepository monitoringRepository) {
    return new AppDetailViewModel(permissionRepository, monitoringRepository);
  }
}
