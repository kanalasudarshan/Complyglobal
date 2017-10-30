package app.complyglobal.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.Random;

import app.complyglobal.R;
import app.complyglobal.activity.NotificationReceiver;
import app.complyglobal.dto.NotificationDTO;
import app.complyglobal.utils.SchedulerUtil;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public NotificationBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NotificationBroadcastReceiver", "Notification called");

        NotificationDTO dto=new NotificationDTO();
        dto.setSummaryText("Due Today");
        dto.setBigContentTitle("Due Today"+intent.getIntExtra("notificatinId",0));
        dto.setNotificationContent("Issue declaration in Form E-1 to the dealer purchasing the goods in the course of inter-state sales");
        notifyJob(context,dto);

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void notifyJob(Context context, NotificationDTO dto) {
        Calendar cal = Calendar.getInstance();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(dto.getNotificationContent());
        bigText.setBigContentTitle(dto.getBigContentTitle());
        bigText.setSummaryText(dto.getSummaryText());

        Notification n  = new NotificationCompat.Builder(context)
                .setContentText(dto.getSummaryText())
                .setContentText(dto.getNotificationContent())
                .setSmallIcon(R.drawable.ic_logo)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setStyle(bigText)
                .build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        Random random = new Random();
        notificationManager.notify(random.nextInt(), n);
    }


}
