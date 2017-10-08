package app.complyglobal.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import app.complyglobal.R;
import app.complyglobal.utils.Constants;

public class Settings extends AppCompatActivity {

    private static final int NOTIFICATION_1=1;
    private static final int NOTIFICATION_2=2;
    private View time_one;
    private View time_two;
    SharedPreferences sharedpreferences;
    private int  notificationId;

    private TextView view_1;
    private TextView view_2;
    private TextView view_3;
    private TextView view_4;
    private TextView view_5;
    private TextView view_6;
    private Calendar calendar=Calendar.getInstance();
    private TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,  int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateNotificationTime();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedpreferences=getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        view_1=(TextView)findViewById(R.id.notification_one_hours);
        view_2=(TextView)findViewById(R.id.notification_one_minuts);
        view_3=(TextView)findViewById(R.id.notification_one_ampm);

        view_4=(TextView)findViewById(R.id.notification_two_hours);
        view_5=(TextView)findViewById(R.id.notification_two_minuts);
        view_6=(TextView)findViewById(R.id.notification_two_ampm);

        time_one=(View)findViewById(R.id.change_notification_time_one);
        time_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(Settings.this, timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false).show();

                notificationId=1;
            }
        });

        time_two=(View)findViewById(R.id.change_notification_time_two);
        time_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(Settings.this, timeSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false).show();
                notificationId=2;

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void updateNotificationTime(){
        switch (notificationId){
            case Settings.NOTIFICATION_1:
                view_1.setText(calendar.get(Calendar.HOUR)+"");
                view_2.setText(calendar.get(Calendar.MINUTE)+"");
                view_3.setText(calendar.get(Calendar.AM_PM)==Calendar.AM?"AM" :"PM");
                break;

            case Settings.NOTIFICATION_2:
                view_4.setText(calendar.get(Calendar.HOUR)+"");
                view_5.setText(calendar.get(Calendar.MINUTE)+"");
                view_6.setText(calendar.get(Calendar.AM_PM)==Calendar.AM?"AM" :"PM");

                break;
        }

    }


}
