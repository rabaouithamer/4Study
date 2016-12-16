package com.rabaouithamer.a4study;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class RefreshStyle extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {

   private  ListView liste ;
   private WaveSwipeRefreshLayout wavelayout ;
    Context context ;
    private DatabBaseHandler base = new DatabBaseHandler(this,null,null,1);


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
       sampleArrayStr =base.extraireDonneContact();
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
            return true;
        }

        if (id == R.id.action_ajouter) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_cc, null);
            dialogBuilder.setView(dialogView);

            final EditText nom = (EditText) dialogView.findViewById(R.id.textnom);
            final EditText contenu = (EditText) dialogView.findViewById(R.id.textcontenu);

            dialogBuilder.setTitle("Ajout de contact");
            dialogBuilder.setMessage("Saisir les coordonnées");
            dialogBuilder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //do something with edt.getText().toString();


                    Contact contact = new Contact(nom.getText().toString(),Long.parseLong(contenu.getText().toString()));
                    ;
                    base.addContact(contact);

                    Intent i = new Intent(RefreshStyle.this, RefreshStyle.class);  //your class
                    startActivity(i);
                   //finish();




                }
            });
            dialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //mana3emlou chay
                    //just el alertDialog tetna77a w barra
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();



        }





        return super.onOptionsItemSelected(item);
    }


}
