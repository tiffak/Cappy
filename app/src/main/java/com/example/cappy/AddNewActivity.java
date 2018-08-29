package com.example.cappy;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Calendar;
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

    private void setSpinner(int spinnerId, String date){
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int spinnerPos = adapter.getPosition(date);
        spinner.setSelection(spinnerPos);
    }
}
