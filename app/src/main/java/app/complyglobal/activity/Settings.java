package app.complyglobal.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import app.complyglobal.R;
import app.complyglobal.dao.NotificationTimeDao;
import app.complyglobal.dto.NotificationDTO;
import app.complyglobal.utils.Constants;
import app.complyglobal.utils.SchedulerUtil;

public class Settings extends AppCompatActivity {

    private static final int NOTIFICATION_1=1;
    private static final int NOTIFICATION_2=2;
    private View time_one;
    private View time_two;
    SharedPreferences sharedpreferences;
    private int  notificationId;
    private NotificationTimeDao dao;
    private TextView view_1;
    private TextView view_2;
    private TextView view_3;
    private TextView view_4;
    private TextView view_5;
    private TextView view_6;
    private Calendar calendar=Calendar.getInstance();
    private Calendar calendar_1=Calendar.getInstance();
    private Calendar calendar_2=Calendar.getInstance();
    private Switch notification_one_switch;
    private Switch notification_two_switch;

    private TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,  int minute) {
            NotificationDTO dto=new NotificationDTO();
            dto.setNotificaitonId(notificationId);

            if(notificationId==Constants.NOTIFICATION_ONE_ID) {
                calendar_1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar_1.set(Calendar.MINUTE, minute);
                dto.setScheduleTime(Constants.simpleDateFormat.format(calendar_1.getTime()));
            }

            if(notificationId==Constants.NOTIFICATION_TWO_ID) {
                calendar_2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar_2.set(Calendar.MINUTE, minute);
                dto.setScheduleTime(Constants.simpleDateFormat.format(calendar_2.getTime()));
            }

            updateNotificationTime();
            dao.updateNotification(dto);

            if(notificationId==Constants.NOTIFICATION_ONE_ID && notification_one_switch.isChecked())
                SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_ONE_REQUEST_CODE,calendar_1,true,false);
            if(notificationId==Constants.NOTIFICATION_TWO_ID && notification_two_switch.isChecked())
                SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_TWO_REQUEST_CODE,calendar_2,true,false);
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

        notification_one_switch=(Switch)findViewById(R.id.notification_switch_one);
        notification_one_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                NotificationDTO dto=new NotificationDTO();
                dto.setNotificaitonId(Settings.NOTIFICATION_1);
                if(compoundButton.isChecked()){
                    dto.setIsActive(Constants.IS_ACTIVE_TRUE);
                    updateNotificationTimeBackground(Settings.NOTIFICATION_1,true);
                    SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_ONE_REQUEST_CODE,calendar_1,true,false);
                }else{
                    dto.setIsActive(Constants.IS_ACTIVE_FALSE);
                    updateNotificationTimeBackground(Settings.NOTIFICATION_1,false);
                    SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_ONE_REQUEST_CODE,calendar_1,true,true);
                }
                dao.setNotificationOff(dto);
            }
        });

        notification_two_switch=(Switch)findViewById(R.id.notification_switch_two);
        notification_two_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                NotificationDTO dto=new NotificationDTO();
                dto.setNotificaitonId(Settings.NOTIFICATION_2);
                if(compoundButton.isChecked()){
                    dto.setIsActive(Constants.IS_ACTIVE_TRUE);
                    updateNotificationTimeBackground(Settings.NOTIFICATION_2,true);
                    SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_TWO_REQUEST_CODE,calendar_2,true,false);
                }else{
                    dto.setIsActive(Constants.IS_ACTIVE_FALSE);
                    updateNotificationTimeBackground(Settings.NOTIFICATION_2,false);
                    SchedulerUtil.scheduleJob(getApplicationContext(), Constants.NOTIFICATION_TWO_REQUEST_CODE,calendar_2,true,true);
                }
                dao.setNotificationOff(dto);
            }
        });

        time_one=(View)findViewById(R.id.change_notification_time_one);
        time_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(Settings.this, timeSetListener,
                        calendar_1.get(Calendar.HOUR_OF_DAY),
                        calendar_1.get(Calendar.MINUTE),
                        false).show();

                notificationId=1;
            }
        });

        time_two=(View)findViewById(R.id.change_notification_time_two);
        time_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(Settings.this, timeSetListener,
                        calendar_2.get(Calendar.HOUR_OF_DAY),
                        calendar_2.get(Calendar.MINUTE),
                        false).show();
                notificationId=2;

            }
        });


        dao=new NotificationTimeDao(this);
        getNotifications();

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
                view_1.setText(calendar_1.get(Calendar.HOUR)+"");
                view_2.setText(calendar_1.get(Calendar.MINUTE)+"");
                view_3.setText(calendar_1.get(Calendar.AM_PM)==Calendar.AM?"AM" :"PM");
                break;

            case Settings.NOTIFICATION_2:
                view_4.setText(calendar_2.get(Calendar.HOUR)+"");
                view_5.setText(calendar_2.get(Calendar.MINUTE)+"");
                view_6.setText(calendar_2.get(Calendar.AM_PM)==Calendar.AM?"AM" :"PM");

                break;
        }
    }


    private void getNotifications(){
        Log.i("Settings","Notification time setting from database");
        NotificationDTO dto_1=dao.getNotificationById(Constants.NOTIFICATION_ONE_ID);
        try{
            Log.i("Settings",dto_1.toString());
            if(dto_1.getIsActive()==Constants.IS_ACTIVE_TRUE){
                notification_one_switch.setChecked(true);
                updateNotificationTimeBackground(Settings.NOTIFICATION_1,true);
            }else{
                notification_one_switch.setChecked(false);
                updateNotificationTimeBackground(Settings.NOTIFICATION_1,false);
            }
            calendar_1.setTime(Constants.simpleDateFormat.parse(dto_1.getScheduleTime()));
            calendar_1.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
            calendar_1.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
            calendar_1.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));

            notificationId=Constants.NOTIFICATION_ONE_ID;
            updateNotificationTime();
        }catch (Exception exception){
            Log.e("Settings","Notificaion 1 parse exception",exception);
        }
        NotificationDTO dto_2=dao.getNotificationById(Constants.NOTIFICATION_TWO_ID);
        try{
            Log.i("Settings",dto_2.toString());
            if(dto_2.getIsActive()==Constants.IS_ACTIVE_TRUE){
                notification_two_switch.setChecked(true);
                updateNotificationTimeBackground(Settings.NOTIFICATION_2,true);
            }else{
                notification_two_switch.setChecked(false);
                updateNotificationTimeBackground(Settings.NOTIFICATION_2,false);
            }
            calendar_2.setTime(Constants.simpleDateFormat.parse(dto_2.getScheduleTime()));
            calendar_2.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
            calendar_2.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
            calendar_2.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
            notificationId=Constants.NOTIFICATION_TWO_ID;
            updateNotificationTime();
        }catch (Exception exception){
            Log.e("Settings","Notificaion 2 parse exception",exception);
        }
    }

    private void updateNotificationTimeBackground(int notificationId,boolean isChecked){
        switch (notificationId){
            case Settings.NOTIFICATION_1:
                if(isChecked) {
                    view_1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                    view_2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                    view_3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                }else{
                    view_1.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    view_2.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    view_3.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                }
                break;

            case Settings.NOTIFICATION_2:
                if(isChecked) {
                    view_4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                    view_5.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                    view_6.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLight));
                }else{
                    view_4.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    view_5.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                    view_6.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
                }
                break;
        }

    }

}
