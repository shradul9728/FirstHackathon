package com.echoguard.privacy.data.repository;

import android.content.Context;
import com.echoguard.privacy.data.database.EchoGuardDatabase;
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
public final class PermissionRepository_Factory implements Factory<PermissionRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<EchoGuardDatabase> databaseProvider;

  public PermissionRepository_Factory(Provider<Context> contextProvider,
      Provider<EchoGuardDatabase> databaseProvider) {
    this.contextProvider = contextProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PermissionRepository get() {
    return newInstance(contextProvider.get(), databaseProvider.get());
  }

  public static PermissionRepository_Factory create(Provider<Context> contextProvider,
      Provider<EchoGuardDatabase> databaseProvider) {
    return new PermissionRepository_Factory(contextProvider, databaseProvider);
  }

  public static PermissionRepository newInstance(Context context, EchoGuardDatabase database) {
    return new PermissionRepository(context, database);
  }
}
