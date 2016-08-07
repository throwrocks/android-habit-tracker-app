package athrow.rocks.android_habit_tracker_app;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Stetho for inspecting the database (chrome://inspect)
        Stetho.initializeWithDefaults(this);
        // Inflate the layout
        setContentView(R.layout.activity_main);
        // Get an instance of the HabitsDBHelper (this creates or upgrades the database if needed)
        HabitsDBHelper habitsDBHelper = new HabitsDBHelper(this);
        // Create a new record
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitsContract.HabitsEntry.habitName, "Perform Siu Nim Tao");
        contentValues.put(HabitsContract.HabitsEntry.habitDateAdded, "8/6/2016");
        contentValues.put(HabitsContract.HabitsEntry.habitCount, 0);
        habitsDBHelper.insertRecord(contentValues);
        // Update the record
        contentValues.put(HabitsContract.HabitsEntry.habitDateLastDone, "8/7/2016");
        contentValues.put(HabitsContract.HabitsEntry.habitCount, 1);
        habitsDBHelper.updateRecord(1, contentValues);
        // Get the record
        Cursor habitRecord = habitsDBHelper.getRecord(1);
        Log.i("getRecord ", habitRecord.toString());
        // Delete the record
        habitsDBHelper.deleteRecord(1);
    }
}
