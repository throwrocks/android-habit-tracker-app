package athrow.rocks.android_habit_tracker_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jose on 8/6/16.
 */
class HabitsDBHelper extends SQLiteOpenHelper {

    // The database version
    private static final int DATABASE_VERSION = 3;

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

    public void insertRecord(ContentValues contentValues) {
        db = getWritableDatabase();
        try {
            db.insert(HabitsContract.HabitsEntry.HABITS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int recordId) {

        try {
            db.delete(
                    HabitsContract.HabitsEntry.HABITS_TABLE_NAME,
                    HabitsContract.HabitsEntry._ID + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}