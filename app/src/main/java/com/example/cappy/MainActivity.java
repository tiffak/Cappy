package com.example.cappy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener {
    private CalendarView mCalendarView;
    protected List<EventDay> events;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frameContainer, fragment).commit();
        // TODO: add addToBackStack()
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mCalendarView = findViewById(R.id.calendarView);
        try {
            mCalendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        events = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null && intent.getStringArrayListExtra("NEW_COURSE_VALUES") != null){
            ArrayList<String> values = intent.getStringArrayListExtra("NEW_COURSE_VALUES");
            Calendar courseDay = Calendar.getInstance();
//            LocalDate localDate = getDate(values.get(1));
//            courseDay.set(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
            events.add(new EventDay(calendar, R.drawable.ic_event_black_24dp));
        }

        mCalendarView.setEvents(events);
    }

//    private LocalDate getDate(String weekday){
//        LocalDate localDate = LocalDate.now();
//        LocalDate targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        if (weekday.equals("Tuesday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.TUESDAY));
//        } else if (weekday.equals("Wednesday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY));
//        } else if (weekday.equals("Thursday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
//        } else if (weekday.equals("Friday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
//        } else if (weekday.equals("Saturday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
//        } else if (weekday.equals("Sunday")){
//            targetDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//        }
//       return targetDate;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, AddNewActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
