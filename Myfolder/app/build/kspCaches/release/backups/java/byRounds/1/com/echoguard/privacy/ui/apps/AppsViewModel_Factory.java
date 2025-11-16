package com.echoguard.privacy.ui.apps;

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
public final class AppsViewModel_Factory implements Factory<AppsViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public AppsViewModel_Factory(Provider<PermissionRepository> permissionRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  @Override
  public AppsViewModel get() {
    return newInstance(permissionRepositoryProvider.get());
  }

  public static AppsViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new AppsViewModel_Factory(permissionRepositoryProvider);
  }

  public static AppsViewModel newInstance(PermissionRepository permissionRepository) {
    return new AppsViewModel(permissionRepository);
  }
}
