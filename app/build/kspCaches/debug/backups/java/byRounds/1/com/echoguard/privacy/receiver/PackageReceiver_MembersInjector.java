package com.echoguard.privacy.receiver;

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
public final class PackageReceiver_MembersInjector implements MembersInjector<PackageReceiver> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public PackageReceiver_MembersInjector(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  public static MembersInjector<PackageReceiver> create(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new PackageReceiver_MembersInjector(permissionRepositoryProvider);
  }

  @Override
  public void injectMembers(PackageReceiver instance) {
    injectPermissionRepository(instance, permissionRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.echoguard.privacy.receiver.PackageReceiver.permissionRepository")
  public static void injectPermissionRepository(PackageReceiver instance,
      PermissionRepository permissionRepository) {
    instance.permissionRepository = permissionRepository;
  }
}
