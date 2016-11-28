package com.rabaouithamer.a4study;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("message","we are in the receiver");

        //fetch extra string from intent
        String extra = intent.getExtras().getString("extra");
        Log.e("what's the key:",extra);

        //intent to the RingtoneService
        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);

        //pass the extra string from alarmActivity to ringtonePlayingService
        serviceIntent.putExtra("extra",extra);

        context.startService(serviceIntent);
    }
}
