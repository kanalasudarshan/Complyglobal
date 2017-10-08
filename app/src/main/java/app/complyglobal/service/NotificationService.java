package app.complyglobal.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

import app.complyglobal.R;

import app.complyglobal.activity.NotificationReceiver;
import app.complyglobal.utils.SchedulerUtil;


public class NotificationService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }



}
