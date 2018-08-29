package com.example.cappy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.applandeo.materialcalendarview.EventDay;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        Toolbar toolbar = findViewById(R.id.add_new_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("New Course");
        ab.setDisplayHomeAsUpEnabled(true);

        Calendar c = Calendar.getInstance();
        String today = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        setSpinner(R.id.weekSpinner, today);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCourse:
                addCourse();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setSpinner(int spinnerId, String date){
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int spinnerPos = adapter.getPosition(date);
        spinner.setSelection(spinnerPos);
    }

    public void addCourse(){
        EditText editText = findViewById(R.id.courseNameEntry);
        String name = editText.getText().toString();
        Spinner spinner = findViewById(R.id.weekSpinner);
        String weekday  = spinner.getSelectedItem().toString();
        TimePicker startTime = findViewById(R.id.timePickerStart);
        int hour = startTime.getHour();
        int min  = startTime.getMinute();
        String time = Integer.toString(hour) + Integer.toString(min);

        Intent intent = new Intent(this, MainActivity.class);
        ArrayList<String> values = new ArrayList<>(Arrays.asList(name, weekday, time));
        intent.putExtra("NEW_COURSE_VALUES", values);
        startActivity(intent);
    }
}
