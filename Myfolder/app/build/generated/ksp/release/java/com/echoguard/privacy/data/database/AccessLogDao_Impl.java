package com.echoguard.privacy.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.echoguard.privacy.data.model.AccessLog;
import com.echoguard.privacy.data.model.AccessType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Long;
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
public final class AccessLogDao_Impl implements AccessLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AccessLog> __insertionAdapterOfAccessLog;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldLogs;

  public AccessLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccessLog = new EntityInsertionAdapter<AccessLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `access_log` (`id`,`packageName`,`appName`,`permission`,`permissionGroup`,`accessType`,`timestamp`,`duration`,`location`,`metadata`,`isBackground`,`riskScore`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AccessLog entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        statement.bindString(4, entity.getPermission());
        statement.bindString(5, entity.getPermissionGroup());
        statement.bindString(6, __AccessType_enumToString(entity.getAccessType()));
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getDuration() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getDuration());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getLocation());
        }
        if (entity.getMetadata() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getMetadata());
        }
        final int _tmp = entity.isBackground() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getRiskScore());
      }
    };
    this.__preparedStmtOfDeleteOldLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM access_log WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAccessLog(final AccessLog log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAccessLog.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAccessLogs(final List<AccessLog> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAccessLog.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldLogs(final long before, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldLogs.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, before);
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
          __preparedStmtOfDeleteOldLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AccessLog>> getRecentAccess(final int limit) {
    final String _sql = "SELECT * FROM access_log ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"access_log"}, new Callable<List<AccessLog>>() {
      @Override
      @NonNull
      public List<AccessLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfPermissionGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionGroup");
          final int _cursorIndexOfAccessType = CursorUtil.getColumnIndexOrThrow(_cursor, "accessType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final int _cursorIndexOfIsBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "isBackground");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final List<AccessLog> _result = new ArrayList<AccessLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccessLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final String _tmpPermissionGroup;
            _tmpPermissionGroup = _cursor.getString(_cursorIndexOfPermissionGroup);
            final AccessType _tmpAccessType;
            _tmpAccessType = __AccessType_stringToEnum(_cursor.getString(_cursorIndexOfAccessType));
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            final boolean _tmpIsBackground;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBackground);
            _tmpIsBackground = _tmp != 0;
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            _item = new AccessLog(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermission,_tmpPermissionGroup,_tmpAccessType,_tmpTimestamp,_tmpDuration,_tmpLocation,_tmpMetadata,_tmpIsBackground,_tmpRiskScore);
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
  public Flow<List<AccessLog>> getAccessByApp(final String packageName) {
    final String _sql = "SELECT * FROM access_log WHERE packageName = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"access_log"}, new Callable<List<AccessLog>>() {
      @Override
      @NonNull
      public List<AccessLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfPermissionGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionGroup");
          final int _cursorIndexOfAccessType = CursorUtil.getColumnIndexOrThrow(_cursor, "accessType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final int _cursorIndexOfIsBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "isBackground");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final List<AccessLog> _result = new ArrayList<AccessLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccessLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final String _tmpPermissionGroup;
            _tmpPermissionGroup = _cursor.getString(_cursorIndexOfPermissionGroup);
            final AccessType _tmpAccessType;
            _tmpAccessType = __AccessType_stringToEnum(_cursor.getString(_cursorIndexOfAccessType));
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            final boolean _tmpIsBackground;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBackground);
            _tmpIsBackground = _tmp != 0;
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            _item = new AccessLog(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermission,_tmpPermissionGroup,_tmpAccessType,_tmpTimestamp,_tmpDuration,_tmpLocation,_tmpMetadata,_tmpIsBackground,_tmpRiskScore);
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
  public Flow<List<AccessLog>> getAccessByPermission(final String permission) {
    final String _sql = "SELECT * FROM access_log WHERE permission = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, permission);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"access_log"}, new Callable<List<AccessLog>>() {
      @Override
      @NonNull
      public List<AccessLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfPermissionGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionGroup");
          final int _cursorIndexOfAccessType = CursorUtil.getColumnIndexOrThrow(_cursor, "accessType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final int _cursorIndexOfIsBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "isBackground");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final List<AccessLog> _result = new ArrayList<AccessLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccessLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final String _tmpPermissionGroup;
            _tmpPermissionGroup = _cursor.getString(_cursorIndexOfPermissionGroup);
            final AccessType _tmpAccessType;
            _tmpAccessType = __AccessType_stringToEnum(_cursor.getString(_cursorIndexOfAccessType));
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            final boolean _tmpIsBackground;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBackground);
            _tmpIsBackground = _tmp != 0;
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            _item = new AccessLog(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermission,_tmpPermissionGroup,_tmpAccessType,_tmpTimestamp,_tmpDuration,_tmpLocation,_tmpMetadata,_tmpIsBackground,_tmpRiskScore);
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
  public Flow<List<AccessLog>> getAccessSince(final long since) {
    final String _sql = "SELECT * FROM access_log WHERE timestamp >= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"access_log"}, new Callable<List<AccessLog>>() {
      @Override
      @NonNull
      public List<AccessLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfPermissionGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionGroup");
          final int _cursorIndexOfAccessType = CursorUtil.getColumnIndexOrThrow(_cursor, "accessType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final int _cursorIndexOfIsBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "isBackground");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final List<AccessLog> _result = new ArrayList<AccessLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccessLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final String _tmpPermissionGroup;
            _tmpPermissionGroup = _cursor.getString(_cursorIndexOfPermissionGroup);
            final AccessType _tmpAccessType;
            _tmpAccessType = __AccessType_stringToEnum(_cursor.getString(_cursorIndexOfAccessType));
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            final boolean _tmpIsBackground;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBackground);
            _tmpIsBackground = _tmp != 0;
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            _item = new AccessLog(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermission,_tmpPermissionGroup,_tmpAccessType,_tmpTimestamp,_tmpDuration,_tmpLocation,_tmpMetadata,_tmpIsBackground,_tmpRiskScore);
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
  public Flow<List<AccessLog>> getBackgroundAccess() {
    final String _sql = "SELECT * FROM access_log WHERE isBackground = 1 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"access_log"}, new Callable<List<AccessLog>>() {
      @Override
      @NonNull
      public List<AccessLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfPermissionGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionGroup");
          final int _cursorIndexOfAccessType = CursorUtil.getColumnIndexOrThrow(_cursor, "accessType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final int _cursorIndexOfIsBackground = CursorUtil.getColumnIndexOrThrow(_cursor, "isBackground");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final List<AccessLog> _result = new ArrayList<AccessLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AccessLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final String _tmpPermissionGroup;
            _tmpPermissionGroup = _cursor.getString(_cursorIndexOfPermissionGroup);
            final AccessType _tmpAccessType;
            _tmpAccessType = __AccessType_stringToEnum(_cursor.getString(_cursorIndexOfAccessType));
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            final boolean _tmpIsBackground;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBackground);
            _tmpIsBackground = _tmp != 0;
            final float _tmpRiskScore;
            _tmpRiskScore = _cursor.getFloat(_cursorIndexOfRiskScore);
            _item = new AccessLog(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermission,_tmpPermissionGroup,_tmpAccessType,_tmpTimestamp,_tmpDuration,_tmpLocation,_tmpMetadata,_tmpIsBackground,_tmpRiskScore);
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
  public Object getAccessCountSince(final long since,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM access_log WHERE timestamp >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
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
  public Object getActiveAppsCountSince(final long since,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT packageName) FROM access_log WHERE timestamp >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
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
  public Object getPermissionAccessStats(final long since,
      final Continuation<? super List<PermissionAccessStat>> $completion) {
    final String _sql = "\n"
            + "        SELECT permission, COUNT(*) as count \n"
            + "        FROM access_log \n"
            + "        WHERE timestamp >= ? \n"
            + "        GROUP BY permission \n"
            + "        ORDER BY count DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PermissionAccessStat>>() {
      @Override
      @NonNull
      public List<PermissionAccessStat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPermission = 0;
          final int _cursorIndexOfCount = 1;
          final List<PermissionAccessStat> _result = new ArrayList<PermissionAccessStat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PermissionAccessStat _item;
            final String _tmpPermission;
            _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new PermissionAccessStat(_tmpPermission,_tmpCount);
            _result.add(_item);
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

  private String __AccessType_enumToString(@NonNull final AccessType _value) {
    switch (_value) {
      case CAMERA_ACCESSED: return "CAMERA_ACCESSED";
      case MICROPHONE_ACCESSED: return "MICROPHONE_ACCESSED";
      case LOCATION_ACCESSED: return "LOCATION_ACCESSED";
      case CONTACTS_READ: return "CONTACTS_READ";
      case SMS_READ: return "SMS_READ";
      case CALL_LOG_READ: return "CALL_LOG_READ";
      case STORAGE_READ: return "STORAGE_READ";
      case STORAGE_WRITE: return "STORAGE_WRITE";
      case PHONE_STATE_READ: return "PHONE_STATE_READ";
      case NETWORK_ACCESS: return "NETWORK_ACCESS";
      case NOTIFICATION_POSTED: return "NOTIFICATION_POSTED";
      case SYSTEM_ALERT_SHOWN: return "SYSTEM_ALERT_SHOWN";
      case DEVICE_ADMIN_USED: return "DEVICE_ADMIN_USED";
      case OTHER: return "OTHER";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private AccessType __AccessType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "CAMERA_ACCESSED": return AccessType.CAMERA_ACCESSED;
      case "MICROPHONE_ACCESSED": return AccessType.MICROPHONE_ACCESSED;
      case "LOCATION_ACCESSED": return AccessType.LOCATION_ACCESSED;
      case "CONTACTS_READ": return AccessType.CONTACTS_READ;
      case "SMS_READ": return AccessType.SMS_READ;
      case "CALL_LOG_READ": return AccessType.CALL_LOG_READ;
      case "STORAGE_READ": return AccessType.STORAGE_READ;
      case "STORAGE_WRITE": return AccessType.STORAGE_WRITE;
      case "PHONE_STATE_READ": return AccessType.PHONE_STATE_READ;
      case "NETWORK_ACCESS": return AccessType.NETWORK_ACCESS;
      case "NOTIFICATION_POSTED": return AccessType.NOTIFICATION_POSTED;
      case "SYSTEM_ALERT_SHOWN": return AccessType.SYSTEM_ALERT_SHOWN;
      case "DEVICE_ADMIN_USED": return AccessType.DEVICE_ADMIN_USED;
      case "OTHER": return AccessType.OTHER;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
