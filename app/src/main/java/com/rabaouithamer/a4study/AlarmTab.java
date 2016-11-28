package com.rabaouithamer.a4study;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static com.rabaouithamer.a4study.R.*;


public class AlarmTab extends Fragment {
    AlarmManager alarmManager;
    TimePicker timePicker;
    Button btnOn;
    Button btnOff;
    TextView tvUpdate;
    Context context;
    Calendar calendar;
    PendingIntent pendingIntent;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(layout.alarm,container,false);
        btnOn = (Button) v.findViewById(R.id.btnOn);
        btnOff = (Button)v.findViewById(id.btnOff);
        tvUpdate = (TextView)v.findViewById(id.tvUpdate);

        timePicker = (TimePicker) v.findViewById(id.timePicker);
        alarmManager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);

        calendar = Calendar.getInstance();

        //intent to AlarmReceiver
       final Intent intent = new Intent(getActivity(),AlarmReceiver.class);
        btnOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String hourString = String.valueOf(hour);
                String minuteString = String.valueOf(minute);

                if(hour>12){
                    hourString = String.valueOf(hour-12);

                }
                if (minute<10){
                    minuteString = "0"+minuteString;
                }

                tvUpdate.setText("Alarm set to:"+hourString+":"+minuteString);

                //tel the clock that you click alarm on
                intent.putExtra("extra","alarm on");

                //pending intent that delayed the intent until the specified calendar time
               pendingIntent = PendingIntent.getBroadcast(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUpdate.setText("Alarm off");

                //cancel the alarm
                alarmManager.cancel(pendingIntent);

                //tel the alarm that you click alarm off
               intent.putExtra("extra","alarm off");

                //stop the ringtone
               getActivity().sendBroadcast(intent);
            }
        });

        return v;
    }
}

