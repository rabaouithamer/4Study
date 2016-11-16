package com.rabaouithamer.a4study;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Random;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class RefreshStyle extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {

   private  ListView liste ;
   private WaveSwipeRefreshLayout wavelayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_style);
        initView();
        setSampleData();
    }

    private void initView(){
        wavelayout = (WaveSwipeRefreshLayout)findViewById(R.id.swipe);
        wavelayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        wavelayout.setOnRefreshListener(this);
        wavelayout.setWaveColor(Color.argb(100,255,0,0));

        liste = (ListView)findViewById(R.id.liste);

    }

    @Override
    protected void onResume() {
        //mWaveSwipeRefreshLayout.setRefreshing(true);
        refresh();
        super.onResume();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void setSampleData() {
        ArrayList<String> sampleArrayStr = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            sampleArrayStr.add("salut :)" );
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, sampleArrayStr);
        liste.setAdapter(adapter);
    }

    private void refresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                wavelayout.setRefreshing(false);
            }
        }, 3000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            wavelayout.setRefreshing(true);
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
