package com.echoguard.privacy.data.preferences;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.echoguard.privacy.data.encryption.EncryptionManager;
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
public final class SettingsManager_Factory implements Factory<SettingsManager> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private final Provider<EncryptionManager> encryptionManagerProvider;

  public SettingsManager_Factory(Provider<DataStore<Preferences>> dataStoreProvider,
      Provider<EncryptionManager> encryptionManagerProvider) {
    this.dataStoreProvider = dataStoreProvider;
    this.encryptionManagerProvider = encryptionManagerProvider;
  }

  @Override
  public SettingsManager get() {
    return newInstance(dataStoreProvider.get(), encryptionManagerProvider.get());
  }

  public static SettingsManager_Factory create(Provider<DataStore<Preferences>> dataStoreProvider,
      Provider<EncryptionManager> encryptionManagerProvider) {
    return new SettingsManager_Factory(dataStoreProvider, encryptionManagerProvider);
  }

  public static SettingsManager newInstance(DataStore<Preferences> dataStore,
      EncryptionManager encryptionManager) {
    return new SettingsManager(dataStore, encryptionManager);
  }
}
