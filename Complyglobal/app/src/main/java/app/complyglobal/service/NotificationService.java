package app.complyglobal.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import app.complyglobal.R;
import app.complyglobal.activity.NotificationReceiver;


public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            handleActionFoo("", "");
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {

        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification n  = new Notification.Builder(this)
                .setContentTitle("Due Today")
                .setContentText("File Annual Return every calendar year on or before 1st February File Annual Return every calendar year on or before 1st February")
                .setSmallIcon(R.drawable.ic_logo)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }

}
