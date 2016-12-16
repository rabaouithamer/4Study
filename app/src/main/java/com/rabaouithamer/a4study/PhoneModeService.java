package com.rabaouithamer.a4study;


import android.Manifest;
import android.app.Service;

import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.telephony.SmsManager;

import java.util.List;


public class PhoneModeService extends Service  {









    public int onStartCommand(Intent intent, int flags, int startId) {

       return START_STICKY;
    }



@Override
    public void onDestroy(){
    super.onDestroy();
    Toast.makeText(getApplicationContext(),"Service Stopped",Toast.LENGTH_LONG);
}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
