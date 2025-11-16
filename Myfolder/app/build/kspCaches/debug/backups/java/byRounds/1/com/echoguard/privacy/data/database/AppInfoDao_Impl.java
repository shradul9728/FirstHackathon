package com.echoguard.privacy.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.echoguard.privacy.data.model.AppInfo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppInfoDao_Impl implements AppInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppInfo> __insertionAdapterOfAppInfo;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<AppInfo> __deletionAdapterOfAppInfo;

  private final EntityDeletionOrUpdateAdapter<AppInfo> __updateAdapterOfAppInfo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAppByPackage;

  public AppInfoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppInfo = new EntityInsertionAdapter<AppInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_info` (`packageName`,`appName`,`versionName`,`versionCode`,`isSystemApp`,`installTime`,`updateTime`,`targetSdkVersion`,`grantedPermissions`,`requestedPermissions`,`riskScore`,`lastScanned`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppInfo entity) {
        statement.bindString(1, entity.getPackageName());
        statement.bindString(2, entity.getAppName());
        statement.bindString(3, entity.getVersionName());
        statement.bindLong(4, entity.getVersionCode());
        final int _tmp = entity.isSystemApp() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getInstallTime());
        statement.bindLong(7, entity.getUpdateTime());
        statement.bindLong(8, entity.getTargetSdkVersion());
        final String _tmp_1 = __converters.fromStringList(entity.getGrantedPermissions());
        statement.bindString(9, _tmp_1);
        final String _tmp_2 = __converters.fromStringList(entity.getRequestedPermissions());
        statement.bindString(10, _tmp_2);
        statement.bindDouble(11, entity.getRiskScore());
        statement.bindLong(12, entity.getLastScanned());
      }
    };
    this.__deletionAdapterOfAppInfo = new EntityDeletionOrUpdateAdapter<AppInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `app_info` WHERE `packageName` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppInfo entity) {
        statement.bindString(1, entity.getPackageName());
      }
    };
    this.__updateAdapterOfAppInfo = new EntityDeletionOrUpdateAdapter<AppInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `app_info` SET `packageName` = ?,`appName` = ?,`versionName` = ?,`versionCode` = ?,`isSystemApp` = ?,`installTime` = ?,`updateTime` = ?,`targetSdkVersion` = ?,`grantedPermissions` = ?,`requestedPermissions` = ?,`riskScore` = ?,`lastScanned` = ? WHERE `packageName` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppInfo entity) {
        statement.bindString(1, entity.getPackageName());
        statement.bindString(2, entity.getAppName());
        statement.bindString(3, entity.getVersionName());
        statement.bindLong(4, entity.getVersionCode());
        final int _tmp = entity.isSystemApp() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getInstallTime());
        statement.bindLong(7, entity.getUpdateTime());
        statement.bindLong(8, entity.getTargetSdkVersion());
        final String _tmp_1 = __converters.fromStringList(entity.getGrantedPermissions());
        statement.bindString(9, _tmp_1);
        final String _tmp_2 = __converters.fromStringList(entity.getRequestedPermissions());
        statement.bindString(10, _tmp_2);
        statement.bindDouble(11, entity.getRiskScore());
        statement.bindLong(12, entity.getLastScanned());
        statement.bindString(13, entity.getPackageName());
      }
    };
    this.__preparedStmtOfDeleteAppByPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_info WHERE packageName = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertApp(final AppInfo app, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppInfo.insert(app);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertApps(final List<AppInfo> apps, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppInfo.insert(apps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteApp(final AppInfo app, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAppInfo.handle(app);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateApp(final AppInfo app, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAppInfo.handle(app);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAppByPackage(final String packageName,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAppByPackage.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, packageName);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAppByPackage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AppInfo>> getAllApps() {
    final String _sql = "SELECT * FROM app_info ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_info"}, new Callable<List<AppInfo>>() {
      @Override
      @NonNull
      public List<AppInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfIsSystemApp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSystemApp");
          final int _cursorIndexOfInstallTime = CursorUtil.getColumnIndexOrThrow(_cursor, "installTime");
          final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfGrantedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "grantedPermissions");
          final int _cursorIndexOfRequestedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedPermissions");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfLastScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScanned");
          final List<AppInfo> _result = new ArrayList<AppInfo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppInfo _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final boolean _tmpIsSystemApp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSystemApp);
            _tmpIsSystemApp = _tmp != 0;
            final long _tmpInstallTime;
            _tmpInstallTime = _cursor.getLong(_cursorIndexOfInstallTime);
            final long _tmpUpdateTime;
            _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final List<String> _tmpGrantedPermissions;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfGrantedPermissions);
            _tmpGrantedPermissions = __converters.toStringList(_tmp_1);
            final List<String> _tmpRequestedPermissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfRequestedPermissions);
            _tmpRequestedPermissions = __converters.toStringList(_tmp_2);
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            final long _tmpLastScanned;
            _tmpLastScanned = _cursor.getLong(_cursorIndexOfLastScanned);
            _item = new AppInfo(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpIsSystemApp,_tmpInstallTime,_tmpUpdateTime,_tmpTargetSdkVersion,_tmpGrantedPermissions,_tmpRequestedPermissions,_tmpRiskScore,_tmpLastScanned);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAppByPackage(final String packageName,
      final Continuation<? super AppInfo> $completion) {
    final String _sql = "SELECT * FROM app_info WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppInfo>() {
      @Override
      @Nullable
      public AppInfo call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfIsSystemApp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSystemApp");
          final int _cursorIndexOfInstallTime = CursorUtil.getColumnIndexOrThrow(_cursor, "installTime");
          final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfGrantedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "grantedPermissions");
          final int _cursorIndexOfRequestedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedPermissions");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfLastScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScanned");
          final AppInfo _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final boolean _tmpIsSystemApp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSystemApp);
            _tmpIsSystemApp = _tmp != 0;
            final long _tmpInstallTime;
            _tmpInstallTime = _cursor.getLong(_cursorIndexOfInstallTime);
            final long _tmpUpdateTime;
            _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final List<String> _tmpGrantedPermissions;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfGrantedPermissions);
            _tmpGrantedPermissions = __converters.toStringList(_tmp_1);
            final List<String> _tmpRequestedPermissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfRequestedPermissions);
            _tmpRequestedPermissions = __converters.toStringList(_tmp_2);
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            final long _tmpLastScanned;
            _tmpLastScanned = _cursor.getLong(_cursorIndexOfLastScanned);
            _result = new AppInfo(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpIsSystemApp,_tmpInstallTime,_tmpUpdateTime,_tmpTargetSdkVersion,_tmpGrantedPermissions,_tmpRequestedPermissions,_tmpRiskScore,_tmpLastScanned);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AppInfo>> getHighRiskApps(final float minRiskScore) {
    final String _sql = "SELECT * FROM app_info WHERE riskScore >= ? ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, minRiskScore);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_info"}, new Callable<List<AppInfo>>() {
      @Override
      @NonNull
      public List<AppInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfIsSystemApp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSystemApp");
          final int _cursorIndexOfInstallTime = CursorUtil.getColumnIndexOrThrow(_cursor, "installTime");
          final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfGrantedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "grantedPermissions");
          final int _cursorIndexOfRequestedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedPermissions");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfLastScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScanned");
          final List<AppInfo> _result = new ArrayList<AppInfo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppInfo _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final boolean _tmpIsSystemApp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSystemApp);
            _tmpIsSystemApp = _tmp != 0;
            final long _tmpInstallTime;
            _tmpInstallTime = _cursor.getLong(_cursorIndexOfInstallTime);
            final long _tmpUpdateTime;
            _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final List<String> _tmpGrantedPermissions;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfGrantedPermissions);
            _tmpGrantedPermissions = __converters.toStringList(_tmp_1);
            final List<String> _tmpRequestedPermissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfRequestedPermissions);
            _tmpRequestedPermissions = __converters.toStringList(_tmp_2);
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            final long _tmpLastScanned;
            _tmpLastScanned = _cursor.getLong(_cursorIndexOfLastScanned);
            _item = new AppInfo(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpIsSystemApp,_tmpInstallTime,_tmpUpdateTime,_tmpTargetSdkVersion,_tmpGrantedPermissions,_tmpRequestedPermissions,_tmpRiskScore,_tmpLastScanned);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AppInfo>> getUserApps() {
    final String _sql = "SELECT * FROM app_info WHERE isSystemApp = 0 ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_info"}, new Callable<List<AppInfo>>() {
      @Override
      @NonNull
      public List<AppInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfIsSystemApp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSystemApp");
          final int _cursorIndexOfInstallTime = CursorUtil.getColumnIndexOrThrow(_cursor, "installTime");
          final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfGrantedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "grantedPermissions");
          final int _cursorIndexOfRequestedPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedPermissions");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfLastScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScanned");
          final List<AppInfo> _result = new ArrayList<AppInfo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppInfo _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final boolean _tmpIsSystemApp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSystemApp);
            _tmpIsSystemApp = _tmp != 0;
            final long _tmpInstallTime;
            _tmpInstallTime = _cursor.getLong(_cursorIndexOfInstallTime);
            final long _tmpUpdateTime;
            _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final List<String> _tmpGrantedPermissions;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfGrantedPermissions);
            _tmpGrantedPermissions = __converters.toStringList(_tmp_1);
            final List<String> _tmpRequestedPermissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfRequestedPermissions);
            _tmpRequestedPermissions = __converters.toStringList(_tmp_2);
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            final long _tmpLastScanned;
            _tmpLastScanned = _cursor.getLong(_cursorIndexOfLastScanned);
            _item = new AppInfo(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpIsSystemApp,_tmpInstallTime,_tmpUpdateTime,_tmpTargetSdkVersion,_tmpGrantedPermissions,_tmpRequestedPermissions,_tmpRiskScore,_tmpLastScanned);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAppCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM app_info";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getHighRiskAppCount(final float minRiskScore,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM app_info WHERE riskScore >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, minRiskScore);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
