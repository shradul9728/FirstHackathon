package com.echoguard.privacy.data.repository;

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
public final class MonitoringRepository_Factory implements Factory<MonitoringRepository> {
  private final Provider<EchoGuardDatabase> databaseProvider;

  public MonitoringRepository_Factory(Provider<EchoGuardDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MonitoringRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static MonitoringRepository_Factory create(Provider<EchoGuardDatabase> databaseProvider) {
    return new MonitoringRepository_Factory(databaseProvider);
  }

  public static MonitoringRepository newInstance(EchoGuardDatabase database) {
    return new MonitoringRepository(database);
  }
}
