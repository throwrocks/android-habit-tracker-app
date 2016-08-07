package athrow.rocks.android_habit_tracker_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jose on 8/6/16.
 */
class HabitsDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "habits.db";
    private SQLiteDatabase db;

    public HabitsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("onCreate", "true");
        final String SQL_CREATE_HABITS_TABLE =
                "CREATE TABLE " +
                        HabitsContract.HabitsEntry.HABITS_TABLE_NAME + " (" +
                        HabitsContract.HabitsEntry.habitName + " TEXT NOT NULL, " +
                        HabitsContract.HabitsEntry.habitCount + " INTEGER NOT NULL, " +
                        HabitsContract.HabitsEntry.habitDateAdded + " TEXT NOT NULL, " +
                        HabitsContract.HabitsEntry.habitDateLastDone + " TEXT NULL" +
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitsContract.HabitsEntry.HABITS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * insertRecord
     *
     * @param contentValues the ContentValues with the data to create the record
     */
    public void insertRecord(ContentValues contentValues) {
        db = getWritableDatabase();
        try {
            db.insert(HabitsContract.HabitsEntry.HABITS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * updateRecord
     *
     * @param recordId      the id of the record to be updated
     * @param contentValues the ContentValues with the new data
     */
    public void updateRecord(int recordId, ContentValues contentValues) {
        db = getWritableDatabase();
        try {
            db.update(
                    HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                    contentValues,
                    HabitsContract.HabitsEntry.habitId + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * deleteRecord
     *
     * @param recordId the id of the record to be deleted
     */
    public void deleteRecord(int recordId) {
        db = getWritableDatabase();
        try {
            db.delete(
                    HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                    HabitsContract.HabitsEntry.habitId + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    /**
     * getRecord
     *
     * @param recordId the id of the record to get
     * @return a Cursor object with the record values
     */
    public Cursor getRecord(int recordId) {
        Cursor record;
        String table = HabitsContract.HabitsEntry.HABITS_TABLE_NAME;
        String selection = HabitsContract.HabitsEntry.habitId + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(recordId)};
        db = getReadableDatabase();
        try {
            record = db.query(true, table, null, selection, selectionArgs, null, null, null, null);
            record.moveToFirst();
            record.close();
            db.close();
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
            return null;
        }
    }
}