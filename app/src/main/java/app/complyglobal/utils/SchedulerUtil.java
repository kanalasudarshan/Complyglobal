package app.complyglobal.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import app.complyglobal.service.NotificationBroadcastReceiver;
import app.complyglobal.service.NotificationService;

/**
 * Created by SUDARSHAN REDDY on 01-10-2017.
 */
public class SchedulerUtil {

    public static void scheduleJob(Context context) {
        Log.i("SchedulerUtil","Notification called");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("app.complyglobal.NOTIFICATION_ACTION");
        boolean alarmUp = (PendingIntent.getBroadcast(context, 100,notificationIntent,PendingIntent.FLAG_NO_CREATE)!=null);
        if(!alarmUp) {
            Log.i("SchedulerUtil","Alarm scheduled");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR, 7);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.AM_PM, Calendar.PM);
            Log.i("SchedulerUtil","Notification trigger time :"+cal.getTime());
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
        }else{
            Log.i("SchedulerUtil","Alarm already running");
        }
    }
}
