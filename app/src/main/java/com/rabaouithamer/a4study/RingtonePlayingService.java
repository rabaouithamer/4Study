package com.rabaouithamer.a4study;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class RingtonePlayingService extends Service{
    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning = false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService","Received start id "+startId+": "+intent);

        //fetch extra string from alarmActivity
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state is:",state);
        // notification
        // set up the notification service
        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // set up an intent that goes to the Main Activity
        Intent intent_main_activity = new Intent(this.getApplicationContext(), CloseAlarm.class);
        // set up a pending intent
        PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);

        // make the notification parameters
        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle("An alarm is going off!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.ic_action_call)
                .setContentIntent(pending_intent_main_activity)
                .setAutoCancel(true)
                .build();

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId==1){
            Log.e("there is no music","and you want start");
            mediaPlayer = MediaPlayer.create(this,R.raw.alarm);
            //start the ringtone
            mediaPlayer.start();
            this.isRunning = true;
            this.startId = 0;

            // set up the start command for the notification
            notify_manager.notify(0, notification_popup);

        }
        else if(this.isRunning && startId==0){
            Log.e("there is music","and you want end");
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }
        else if (this.isRunning && startId==1){
            Log.e("there is music","and you want start");
            this.isRunning = true;
            this.startId = 1;
        }
        else if (!this.isRunning && startId==0){
            Log.e("there is no music","and you want end");
            this.isRunning = false;
            this.startId = 0;
        }

        else{
            Log.e("msg","hello");

        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        Log.e("destroy","on destroy is called");
        super.onDestroy();
        this.isRunning = false;
    }
}
