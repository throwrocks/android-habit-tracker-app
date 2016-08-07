package athrow.rocks.android_habit_tracker_app;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        // Get an instance of the HabitsDBHelper (this creates, upgrades the database if needed)
        HabitsDBHelper habitsDBHelper = new HabitsDBHelper(this);
        // Create a new ContentValues object with data to add a new record
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitsContract.HabitsEntry.habitName, "Perform Siu Nim Tao");
        contentValues.put(HabitsContract.HabitsEntry.habitDateAdded, "8/6/2016");
        contentValues.put(HabitsContract.HabitsEntry.habitCount, 0);
        // Create a new record
        habitsDBHelper.insertRecord(contentValues);

    }
}
