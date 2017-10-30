package app.complyglobal.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import app.complyglobal.dto.NotificationDTO;
import app.complyglobal.service.NotificationBroadcastReceiver;
import app.complyglobal.service.NotificationService;

/**
 * Created by SUDARSHAN REDDY on 01-10-2017.
 */
public class SchedulerUtil {

    public static void scheduleJob(Context context, int requestCode,Calendar cal,boolean isUpdate,boolean off_notification) {
        Log.i("SchedulerUtil","Notification called");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("app.complyglobal.NOTIFICATION_ACTION");
        notificationIntent.putExtra("notificatinId",requestCode);
        boolean alarmUp = (PendingIntent.getBroadcast(context, requestCode,notificationIntent,PendingIntent.FLAG_NO_CREATE)!=null);
        if(!alarmUp) {
            Log.i("SchedulerUtil","Alarm scheduled");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.i("SchedulerUtil","Notification trigger time :"+cal.getTime());
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
        }else{
            Log.i("SchedulerUtil","Alarm already running");
            if(isUpdate){
                if(off_notification) {
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, requestCode, notificationIntent, PendingIntent.FLAG_NO_CREATE);
                    Log.i("SchedulerUtil", "Notification switched off");
                    alarmManager.cancel(broadcast);
                }else{
                    PendingIntent broadcast = PendingIntent.getBroadcast(context, requestCode, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                    Log.i("SchedulerUtil", "Notification trigger time update :" + cal.getTime());
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, broadcast);
                }
            }
        }
    }
}
