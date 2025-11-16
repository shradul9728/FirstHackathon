package com.echoguard.privacy.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.echoguard.privacy.data.model.PrivacyScore;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
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
public final class PrivacyScoreDao_Impl implements PrivacyScoreDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PrivacyScore> __insertionAdapterOfPrivacyScore;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldScores;

  public PrivacyScoreDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrivacyScore = new EntityInsertionAdapter<PrivacyScore>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `privacy_score` (`date`,`overallScore`,`appCount`,`highRiskAppCount`,`totalPermissions`,`sensitivPermissions`,`recentAlerts`,`improvements`,`timestamp`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrivacyScore entity) {
        statement.bindString(1, entity.getDate());
        statement.bindDouble(2, entity.getOverallScore());
        statement.bindLong(3, entity.getAppCount());
        statement.bindLong(4, entity.getHighRiskAppCount());
        statement.bindLong(5, entity.getTotalPermissions());
        statement.bindLong(6, entity.getSensitivPermissions());
        statement.bindLong(7, entity.getRecentAlerts());
        final String _tmp = __converters.fromStringList(entity.getImprovements());
        statement.bindString(8, _tmp);
        statement.bindLong(9, entity.getTimestamp());
      }
    };
    this.__preparedStmtOfDeleteOldScores = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM privacy_score WHERE date < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertScore(final PrivacyScore score,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPrivacyScore.insert(score);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldScores(final String before, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldScores.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, before);
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
          __preparedStmtOfDeleteOldScores.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PrivacyScore>> getAllScores() {
    final String _sql = "SELECT * FROM privacy_score ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"privacy_score"}, new Callable<List<PrivacyScore>>() {
      @Override
      @NonNull
      public List<PrivacyScore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "overallScore");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "appCount");
          final int _cursorIndexOfHighRiskAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highRiskAppCount");
          final int _cursorIndexOfTotalPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissions");
          final int _cursorIndexOfSensitivPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "sensitivPermissions");
          final int _cursorIndexOfRecentAlerts = CursorUtil.getColumnIndexOrThrow(_cursor, "recentAlerts");
          final int _cursorIndexOfImprovements = CursorUtil.getColumnIndexOrThrow(_cursor, "improvements");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<PrivacyScore> _result = new ArrayList<PrivacyScore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyScore _item;
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpOverallScore;
            _tmpOverallScore = _cursor.getFloat(_cursorIndexOfOverallScore);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final int _tmpHighRiskAppCount;
            _tmpHighRiskAppCount = _cursor.getInt(_cursorIndexOfHighRiskAppCount);
            final int _tmpTotalPermissions;
            _tmpTotalPermissions = _cursor.getInt(_cursorIndexOfTotalPermissions);
            final int _tmpSensitivPermissions;
            _tmpSensitivPermissions = _cursor.getInt(_cursorIndexOfSensitivPermissions);
            final int _tmpRecentAlerts;
            _tmpRecentAlerts = _cursor.getInt(_cursorIndexOfRecentAlerts);
            final List<String> _tmpImprovements;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfImprovements);
            _tmpImprovements = __converters.toStringList(_tmp);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new PrivacyScore(_tmpDate,_tmpOverallScore,_tmpAppCount,_tmpHighRiskAppCount,_tmpTotalPermissions,_tmpSensitivPermissions,_tmpRecentAlerts,_tmpImprovements,_tmpTimestamp);
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
  public Object getScoreByDate(final String date,
      final Continuation<? super PrivacyScore> $completion) {
    final String _sql = "SELECT * FROM privacy_score WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PrivacyScore>() {
      @Override
      @Nullable
      public PrivacyScore call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "overallScore");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "appCount");
          final int _cursorIndexOfHighRiskAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highRiskAppCount");
          final int _cursorIndexOfTotalPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissions");
          final int _cursorIndexOfSensitivPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "sensitivPermissions");
          final int _cursorIndexOfRecentAlerts = CursorUtil.getColumnIndexOrThrow(_cursor, "recentAlerts");
          final int _cursorIndexOfImprovements = CursorUtil.getColumnIndexOrThrow(_cursor, "improvements");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final PrivacyScore _result;
          if (_cursor.moveToFirst()) {
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpOverallScore;
            _tmpOverallScore = _cursor.getFloat(_cursorIndexOfOverallScore);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final int _tmpHighRiskAppCount;
            _tmpHighRiskAppCount = _cursor.getInt(_cursorIndexOfHighRiskAppCount);
            final int _tmpTotalPermissions;
            _tmpTotalPermissions = _cursor.getInt(_cursorIndexOfTotalPermissions);
            final int _tmpSensitivPermissions;
            _tmpSensitivPermissions = _cursor.getInt(_cursorIndexOfSensitivPermissions);
            final int _tmpRecentAlerts;
            _tmpRecentAlerts = _cursor.getInt(_cursorIndexOfRecentAlerts);
            final List<String> _tmpImprovements;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfImprovements);
            _tmpImprovements = __converters.toStringList(_tmp);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new PrivacyScore(_tmpDate,_tmpOverallScore,_tmpAppCount,_tmpHighRiskAppCount,_tmpTotalPermissions,_tmpSensitivPermissions,_tmpRecentAlerts,_tmpImprovements,_tmpTimestamp);
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
  public Object getRecentScores(final int limit,
      final Continuation<? super List<PrivacyScore>> $completion) {
    final String _sql = "SELECT * FROM privacy_score ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PrivacyScore>>() {
      @Override
      @NonNull
      public List<PrivacyScore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "overallScore");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "appCount");
          final int _cursorIndexOfHighRiskAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highRiskAppCount");
          final int _cursorIndexOfTotalPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissions");
          final int _cursorIndexOfSensitivPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "sensitivPermissions");
          final int _cursorIndexOfRecentAlerts = CursorUtil.getColumnIndexOrThrow(_cursor, "recentAlerts");
          final int _cursorIndexOfImprovements = CursorUtil.getColumnIndexOrThrow(_cursor, "improvements");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<PrivacyScore> _result = new ArrayList<PrivacyScore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrivacyScore _item;
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpOverallScore;
            _tmpOverallScore = _cursor.getFloat(_cursorIndexOfOverallScore);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final int _tmpHighRiskAppCount;
            _tmpHighRiskAppCount = _cursor.getInt(_cursorIndexOfHighRiskAppCount);
            final int _tmpTotalPermissions;
            _tmpTotalPermissions = _cursor.getInt(_cursorIndexOfTotalPermissions);
            final int _tmpSensitivPermissions;
            _tmpSensitivPermissions = _cursor.getInt(_cursorIndexOfSensitivPermissions);
            final int _tmpRecentAlerts;
            _tmpRecentAlerts = _cursor.getInt(_cursorIndexOfRecentAlerts);
            final List<String> _tmpImprovements;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfImprovements);
            _tmpImprovements = __converters.toStringList(_tmp);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new PrivacyScore(_tmpDate,_tmpOverallScore,_tmpAppCount,_tmpHighRiskAppCount,_tmpTotalPermissions,_tmpSensitivPermissions,_tmpRecentAlerts,_tmpImprovements,_tmpTimestamp);
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

  @Override
  public Object getAverageScoreSince(final String since,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT AVG(overallScore) FROM privacy_score WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Object getLatestScore(final Continuation<? super PrivacyScore> $completion) {
    final String _sql = "SELECT * FROM privacy_score ORDER BY date DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PrivacyScore>() {
      @Override
      @Nullable
      public PrivacyScore call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "overallScore");
          final int _cursorIndexOfAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "appCount");
          final int _cursorIndexOfHighRiskAppCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highRiskAppCount");
          final int _cursorIndexOfTotalPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissions");
          final int _cursorIndexOfSensitivPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "sensitivPermissions");
          final int _cursorIndexOfRecentAlerts = CursorUtil.getColumnIndexOrThrow(_cursor, "recentAlerts");
          final int _cursorIndexOfImprovements = CursorUtil.getColumnIndexOrThrow(_cursor, "improvements");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final PrivacyScore _result;
          if (_cursor.moveToFirst()) {
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final float _tmpOverallScore;
            _tmpOverallScore = _cursor.getFloat(_cursorIndexOfOverallScore);
            final int _tmpAppCount;
            _tmpAppCount = _cursor.getInt(_cursorIndexOfAppCount);
            final int _tmpHighRiskAppCount;
            _tmpHighRiskAppCount = _cursor.getInt(_cursorIndexOfHighRiskAppCount);
            final int _tmpTotalPermissions;
            _tmpTotalPermissions = _cursor.getInt(_cursorIndexOfTotalPermissions);
            final int _tmpSensitivPermissions;
            _tmpSensitivPermissions = _cursor.getInt(_cursorIndexOfSensitivPermissions);
            final int _tmpRecentAlerts;
            _tmpRecentAlerts = _cursor.getInt(_cursorIndexOfRecentAlerts);
            final List<String> _tmpImprovements;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfImprovements);
            _tmpImprovements = __converters.toStringList(_tmp);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new PrivacyScore(_tmpDate,_tmpOverallScore,_tmpAppCount,_tmpHighRiskAppCount,_tmpTotalPermissions,_tmpSensitivPermissions,_tmpRecentAlerts,_tmpImprovements,_tmpTimestamp);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
