package com.echoguard.privacy.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EchoGuardDatabase_Impl extends EchoGuardDatabase {
  private volatile AppInfoDao _appInfoDao;

  private volatile AccessLogDao _accessLogDao;

  private volatile PrivacyAlertDao _privacyAlertDao;

  private volatile PrivacyScoreDao _privacyScoreDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_info` (`packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `versionName` TEXT NOT NULL, `versionCode` INTEGER NOT NULL, `isSystemApp` INTEGER NOT NULL, `installTime` INTEGER NOT NULL, `updateTime` INTEGER NOT NULL, `targetSdkVersion` INTEGER NOT NULL, `grantedPermissions` TEXT NOT NULL, `requestedPermissions` TEXT NOT NULL, `riskScore` REAL NOT NULL, `lastScanned` INTEGER NOT NULL, PRIMARY KEY(`packageName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `access_log` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `permission` TEXT NOT NULL, `permissionGroup` TEXT NOT NULL, `accessType` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `duration` INTEGER, `location` TEXT, `metadata` TEXT, `isBackground` INTEGER NOT NULL, `riskScore` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `privacy_alert` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `alertType` TEXT NOT NULL, `severity` TEXT NOT NULL, `packageName` TEXT, `permission` TEXT, `timestamp` INTEGER NOT NULL, `isRead` INTEGER NOT NULL, `isDismissed` INTEGER NOT NULL, `actionTaken` TEXT, `metadata` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `privacy_score` (`date` TEXT NOT NULL, `overallScore` REAL NOT NULL, `appCount` INTEGER NOT NULL, `highRiskAppCount` INTEGER NOT NULL, `totalPermissions` INTEGER NOT NULL, `sensitivPermissions` INTEGER NOT NULL, `recentAlerts` INTEGER NOT NULL, `improvements` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`date`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6a8c05d66bf13d839619816b447ea37c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `app_info`");
        db.execSQL("DROP TABLE IF EXISTS `access_log`");
        db.execSQL("DROP TABLE IF EXISTS `privacy_alert`");
        db.execSQL("DROP TABLE IF EXISTS `privacy_score`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAppInfo = new HashMap<String, TableInfo.Column>(12);
        _columnsAppInfo.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("versionName", new TableInfo.Column("versionName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("isSystemApp", new TableInfo.Column("isSystemApp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("installTime", new TableInfo.Column("installTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("updateTime", new TableInfo.Column("updateTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("targetSdkVersion", new TableInfo.Column("targetSdkVersion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("grantedPermissions", new TableInfo.Column("grantedPermissions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("requestedPermissions", new TableInfo.Column("requestedPermissions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("riskScore", new TableInfo.Column("riskScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppInfo.put("lastScanned", new TableInfo.Column("lastScanned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppInfo = new TableInfo("app_info", _columnsAppInfo, _foreignKeysAppInfo, _indicesAppInfo);
        final TableInfo _existingAppInfo = TableInfo.read(db, "app_info");
        if (!_infoAppInfo.equals(_existingAppInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "app_info(com.echoguard.privacy.data.model.AppInfo).\n"
                  + " Expected:\n" + _infoAppInfo + "\n"
                  + " Found:\n" + _existingAppInfo);
        }
        final HashMap<String, TableInfo.Column> _columnsAccessLog = new HashMap<String, TableInfo.Column>(12);
        _columnsAccessLog.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("permission", new TableInfo.Column("permission", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("permissionGroup", new TableInfo.Column("permissionGroup", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("accessType", new TableInfo.Column("accessType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("duration", new TableInfo.Column("duration", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("metadata", new TableInfo.Column("metadata", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("isBackground", new TableInfo.Column("isBackground", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccessLog.put("riskScore", new TableInfo.Column("riskScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccessLog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccessLog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccessLog = new TableInfo("access_log", _columnsAccessLog, _foreignKeysAccessLog, _indicesAccessLog);
        final TableInfo _existingAccessLog = TableInfo.read(db, "access_log");
        if (!_infoAccessLog.equals(_existingAccessLog)) {
          return new RoomOpenHelper.ValidationResult(false, "access_log(com.echoguard.privacy.data.model.AccessLog).\n"
                  + " Expected:\n" + _infoAccessLog + "\n"
                  + " Found:\n" + _existingAccessLog);
        }
        final HashMap<String, TableInfo.Column> _columnsPrivacyAlert = new HashMap<String, TableInfo.Column>(12);
        _columnsPrivacyAlert.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("alertType", new TableInfo.Column("alertType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("packageName", new TableInfo.Column("packageName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("permission", new TableInfo.Column("permission", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("isDismissed", new TableInfo.Column("isDismissed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("actionTaken", new TableInfo.Column("actionTaken", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyAlert.put("metadata", new TableInfo.Column("metadata", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrivacyAlert = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrivacyAlert = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrivacyAlert = new TableInfo("privacy_alert", _columnsPrivacyAlert, _foreignKeysPrivacyAlert, _indicesPrivacyAlert);
        final TableInfo _existingPrivacyAlert = TableInfo.read(db, "privacy_alert");
        if (!_infoPrivacyAlert.equals(_existingPrivacyAlert)) {
          return new RoomOpenHelper.ValidationResult(false, "privacy_alert(com.echoguard.privacy.data.model.PrivacyAlert).\n"
                  + " Expected:\n" + _infoPrivacyAlert + "\n"
                  + " Found:\n" + _existingPrivacyAlert);
        }
        final HashMap<String, TableInfo.Column> _columnsPrivacyScore = new HashMap<String, TableInfo.Column>(9);
        _columnsPrivacyScore.put("date", new TableInfo.Column("date", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("overallScore", new TableInfo.Column("overallScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("appCount", new TableInfo.Column("appCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("highRiskAppCount", new TableInfo.Column("highRiskAppCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("totalPermissions", new TableInfo.Column("totalPermissions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("sensitivPermissions", new TableInfo.Column("sensitivPermissions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("recentAlerts", new TableInfo.Column("recentAlerts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("improvements", new TableInfo.Column("improvements", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrivacyScore.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrivacyScore = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrivacyScore = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrivacyScore = new TableInfo("privacy_score", _columnsPrivacyScore, _foreignKeysPrivacyScore, _indicesPrivacyScore);
        final TableInfo _existingPrivacyScore = TableInfo.read(db, "privacy_score");
        if (!_infoPrivacyScore.equals(_existingPrivacyScore)) {
          return new RoomOpenHelper.ValidationResult(false, "privacy_score(com.echoguard.privacy.data.model.PrivacyScore).\n"
                  + " Expected:\n" + _infoPrivacyScore + "\n"
                  + " Found:\n" + _existingPrivacyScore);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6a8c05d66bf13d839619816b447ea37c", "47b66053df7bcec4cc610a3d7802ddf9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "app_info","access_log","privacy_alert","privacy_score");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `app_info`");
      _db.execSQL("DELETE FROM `access_log`");
      _db.execSQL("DELETE FROM `privacy_alert`");
      _db.execSQL("DELETE FROM `privacy_score`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppInfoDao.class, AppInfoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AccessLogDao.class, AccessLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrivacyAlertDao.class, PrivacyAlertDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrivacyScoreDao.class, PrivacyScoreDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppInfoDao appInfoDao() {
    if (_appInfoDao != null) {
      return _appInfoDao;
    } else {
      synchronized(this) {
        if(_appInfoDao == null) {
          _appInfoDao = new AppInfoDao_Impl(this);
        }
        return _appInfoDao;
      }
    }
  }

  @Override
  public AccessLogDao accessLogDao() {
    if (_accessLogDao != null) {
      return _accessLogDao;
    } else {
      synchronized(this) {
        if(_accessLogDao == null) {
          _accessLogDao = new AccessLogDao_Impl(this);
        }
        return _accessLogDao;
      }
    }
  }

  @Override
  public PrivacyAlertDao privacyAlertDao() {
    if (_privacyAlertDao != null) {
      return _privacyAlertDao;
    } else {
      synchronized(this) {
        if(_privacyAlertDao == null) {
          _privacyAlertDao = new PrivacyAlertDao_Impl(this);
        }
        return _privacyAlertDao;
      }
    }
  }

  @Override
  public PrivacyScoreDao privacyScoreDao() {
    if (_privacyScoreDao != null) {
      return _privacyScoreDao;
    } else {
      synchronized(this) {
        if(_privacyScoreDao == null) {
          _privacyScoreDao = new PrivacyScoreDao_Impl(this);
        }
        return _privacyScoreDao;
      }
    }
  }
}
