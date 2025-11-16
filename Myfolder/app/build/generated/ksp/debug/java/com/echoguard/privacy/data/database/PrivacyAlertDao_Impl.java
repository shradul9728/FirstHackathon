package com.echoguard.privacy.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.echoguard.privacy.data.model.AlertSeverity;
import com.echoguard.privacy.data.model.AlertType;
import com.echoguard.privacy.data.model.PrivacyAlert;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
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
public final class PrivacyAlertDao_Impl implements PrivacyAlertDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PrivacyAlert> __insertionAdapterOfPrivacyAlert;

  private final EntityDeletionOrUpdateAdapter<PrivacyAlert> __updateAdapterOfPrivacyAlert;

  private final SharedSQLiteStatement __preparedStmtOfMarkAlertAsRead;

  private final SharedSQLiteStatement __preparedStmtOfDismissAlert;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldAlerts;

  public PrivacyAlertDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrivacyAlert = new EntityInsertionAdapter<PrivacyAlert>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `privacy_alert` (`id`,`title`,`message`,`alertType`,`severity`,`packageName`,`permission`,`timestamp`,`isRead`,`isDismissed`,`actionTaken`,`metadata`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrivacyAlert entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getMessage());
        statement.bindString(4, __AlertType_enumToString(entity.getAlertType()));
        statement.bindString(5, __AlertSeverity_enumToString(entity.getSeverity()));
        if (entity.getPackageName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPackageName());
        }
        if (entity.getPermission() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPermission());
        }
        statement.bindLong(8, entity.getTimestamp());
        final int _tmp = entity.isRead() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isDismissed() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getActionTaken() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getActionTaken());
        }
        if (entity.getMetadata() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getMetadata());
        }
      }
    };
    this.__updateAdapterOfPrivacyAlert = new EntityDeletionOrUpdateAdapter<PrivacyAlert>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `privacy_alert` SET `id` = ?,`title` = ?,`message` = ?,`alertType` = ?,`severity` = ?,`packageName` = ?,`permission` = ?,`timestamp` = ?,`isRead` = ?,`isDismissed` = ?,`actionTaken` = ?,`metadata` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrivacyAlert entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getMessage());
        statement.bindString(4, __AlertType_enumToString(entity.getAlertType()));
        statement.bindString(5, __AlertSeverity_enumToString(entity.getSeverity()));
        if (entity.getPackageName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPackageName());
        }
        if (entity.getPermission() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getPermission());
        }
        statement.bindLong(8, entity.getTimestamp());
        final int _tmp = entity.isRead() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isDismissed() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getActionTaken() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getActionTaken());
        }
        if (entity.getMetadata() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getMetadata());
        }
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfMarkAlertAsRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE privacy_alert SET isRead = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDismissAlert = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE privacy_alert SET isDismissed = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldAlerts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM privacy_alert WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAlert(final PrivacyAlert alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPrivacyAlert.insert(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlert(final PrivacyAlert alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPrivacyAlert.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAlertAsRead(final long alertId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAlertAsRead.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfMarkAlertAsRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object dismissAlert(final long alertId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDismissAlert.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfDismissAlert.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldAlerts(final long before, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldAlerts.acquire();
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
          __preparedStmtOfDeleteOldAlerts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PrivacyAlert>> getAllAlerts() {
    final String _sql = "SELECT * FROM privacy_alert ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"privacy_alert"}, new Callable<List<PrivacyAlert>>() {
      @Override
      @NonNull
      public List<PrivacyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final List<PrivacyAlert> _result = new ArrayList<PrivacyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final AlertType _tmpAlertType;
            _tmpAlertType = __AlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final AlertSeverity _tmpSeverity;
            _tmpSeverity = __AlertSeverity_stringToEnum(_cursor.getString(_cursorIndexOfSeverity));
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpPermission;
            if (_cursor.isNull(_cursorIndexOfPermission)) {
              _tmpPermission = null;
            } else {
              _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_1 != 0;
            final String _tmpActionTaken;
            if (_cursor.isNull(_cursorIndexOfActionTaken)) {
              _tmpActionTaken = null;
            } else {
              _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            _item = new PrivacyAlert(_tmpId,_tmpTitle,_tmpMessage,_tmpAlertType,_tmpSeverity,_tmpPackageName,_tmpPermission,_tmpTimestamp,_tmpIsRead,_tmpIsDismissed,_tmpActionTaken,_tmpMetadata);
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
  public Flow<List<PrivacyAlert>> getUnreadAlerts() {
    final String _sql = "SELECT * FROM privacy_alert WHERE isRead = 0 ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"privacy_alert"}, new Callable<List<PrivacyAlert>>() {
      @Override
      @NonNull
      public List<PrivacyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final List<PrivacyAlert> _result = new ArrayList<PrivacyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final AlertType _tmpAlertType;
            _tmpAlertType = __AlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final AlertSeverity _tmpSeverity;
            _tmpSeverity = __AlertSeverity_stringToEnum(_cursor.getString(_cursorIndexOfSeverity));
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpPermission;
            if (_cursor.isNull(_cursorIndexOfPermission)) {
              _tmpPermission = null;
            } else {
              _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_1 != 0;
            final String _tmpActionTaken;
            if (_cursor.isNull(_cursorIndexOfActionTaken)) {
              _tmpActionTaken = null;
            } else {
              _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            _item = new PrivacyAlert(_tmpId,_tmpTitle,_tmpMessage,_tmpAlertType,_tmpSeverity,_tmpPackageName,_tmpPermission,_tmpTimestamp,_tmpIsRead,_tmpIsDismissed,_tmpActionTaken,_tmpMetadata);
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
  public Flow<List<PrivacyAlert>> getActiveAlerts() {
    final String _sql = "SELECT * FROM privacy_alert WHERE isDismissed = 0 ORDER BY severity DESC, timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"privacy_alert"}, new Callable<List<PrivacyAlert>>() {
      @Override
      @NonNull
      public List<PrivacyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final List<PrivacyAlert> _result = new ArrayList<PrivacyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final AlertType _tmpAlertType;
            _tmpAlertType = __AlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final AlertSeverity _tmpSeverity;
            _tmpSeverity = __AlertSeverity_stringToEnum(_cursor.getString(_cursorIndexOfSeverity));
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpPermission;
            if (_cursor.isNull(_cursorIndexOfPermission)) {
              _tmpPermission = null;
            } else {
              _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_1 != 0;
            final String _tmpActionTaken;
            if (_cursor.isNull(_cursorIndexOfActionTaken)) {
              _tmpActionTaken = null;
            } else {
              _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            _item = new PrivacyAlert(_tmpId,_tmpTitle,_tmpMessage,_tmpAlertType,_tmpSeverity,_tmpPackageName,_tmpPermission,_tmpTimestamp,_tmpIsRead,_tmpIsDismissed,_tmpActionTaken,_tmpMetadata);
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
  public Flow<List<PrivacyAlert>> getAlertsByApp(final String packageName) {
    final String _sql = "SELECT * FROM privacy_alert WHERE packageName = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"privacy_alert"}, new Callable<List<PrivacyAlert>>() {
      @Override
      @NonNull
      public List<PrivacyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfPermission = CursorUtil.getColumnIndexOrThrow(_cursor, "permission");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfActionTaken = CursorUtil.getColumnIndexOrThrow(_cursor, "actionTaken");
          final int _cursorIndexOfMetadata = CursorUtil.getColumnIndexOrThrow(_cursor, "metadata");
          final List<PrivacyAlert> _result = new ArrayList<PrivacyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final AlertType _tmpAlertType;
            _tmpAlertType = __AlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final AlertSeverity _tmpSeverity;
            _tmpSeverity = __AlertSeverity_stringToEnum(_cursor.getString(_cursorIndexOfSeverity));
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpPermission;
            if (_cursor.isNull(_cursorIndexOfPermission)) {
              _tmpPermission = null;
            } else {
              _tmpPermission = _cursor.getString(_cursorIndexOfPermission);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsRead;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_1 != 0;
            final String _tmpActionTaken;
            if (_cursor.isNull(_cursorIndexOfActionTaken)) {
              _tmpActionTaken = null;
            } else {
              _tmpActionTaken = _cursor.getString(_cursorIndexOfActionTaken);
            }
            final String _tmpMetadata;
            if (_cursor.isNull(_cursorIndexOfMetadata)) {
              _tmpMetadata = null;
            } else {
              _tmpMetadata = _cursor.getString(_cursorIndexOfMetadata);
            }
            _item = new PrivacyAlert(_tmpId,_tmpTitle,_tmpMessage,_tmpAlertType,_tmpSeverity,_tmpPackageName,_tmpPermission,_tmpTimestamp,_tmpIsRead,_tmpIsDismissed,_tmpActionTaken,_tmpMetadata);
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
  public Object getUnreadAlertCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM privacy_alert WHERE isRead = 0";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __AlertType_enumToString(@NonNull final AlertType _value) {
    switch (_value) {
      case NEW_HIGH_RISK_APP: return "NEW_HIGH_RISK_APP";
      case SUSPICIOUS_PERMISSION_ACCESS: return "SUSPICIOUS_PERMISSION_ACCESS";
      case BACKGROUND_LOCATION_ACCESS: return "BACKGROUND_LOCATION_ACCESS";
      case EXCESSIVE_PERMISSIONS: return "EXCESSIVE_PERMISSIONS";
      case NEW_PERMISSION_GRANTED: return "NEW_PERMISSION_GRANTED";
      case PRIVACY_POLICY_CHANGED: return "PRIVACY_POLICY_CHANGED";
      case SECURITY_THREAT_DETECTED: return "SECURITY_THREAT_DETECTED";
      case SYSTEM_PERMISSION_CHANGE: return "SYSTEM_PERMISSION_CHANGE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private String __AlertSeverity_enumToString(@NonNull final AlertSeverity _value) {
    switch (_value) {
      case INFO: return "INFO";
      case WARNING: return "WARNING";
      case HIGH: return "HIGH";
      case CRITICAL: return "CRITICAL";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private AlertType __AlertType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "NEW_HIGH_RISK_APP": return AlertType.NEW_HIGH_RISK_APP;
      case "SUSPICIOUS_PERMISSION_ACCESS": return AlertType.SUSPICIOUS_PERMISSION_ACCESS;
      case "BACKGROUND_LOCATION_ACCESS": return AlertType.BACKGROUND_LOCATION_ACCESS;
      case "EXCESSIVE_PERMISSIONS": return AlertType.EXCESSIVE_PERMISSIONS;
      case "NEW_PERMISSION_GRANTED": return AlertType.NEW_PERMISSION_GRANTED;
      case "PRIVACY_POLICY_CHANGED": return AlertType.PRIVACY_POLICY_CHANGED;
      case "SECURITY_THREAT_DETECTED": return AlertType.SECURITY_THREAT_DETECTED;
      case "SYSTEM_PERMISSION_CHANGE": return AlertType.SYSTEM_PERMISSION_CHANGE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private AlertSeverity __AlertSeverity_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "INFO": return AlertSeverity.INFO;
      case "WARNING": return AlertSeverity.WARNING;
      case "HIGH": return AlertSeverity.HIGH;
      case "CRITICAL": return AlertSeverity.CRITICAL;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
