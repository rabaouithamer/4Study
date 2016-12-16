package com.rabaouithamer.a4study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cc extends AppCompatActivity {
    DatabBaseHandler base = new DatabBaseHandler(this, null, null, 1);
    private Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText var1 = (EditText) findViewById(R.id.textnom);
        final EditText var2 = (EditText) findViewById(R.id.textcontenu);
        final Button bouton = (Button) findViewById(R.id.button1);

        Intent intent = getIntent();
        String mess = intent.getStringExtra("nom");
        String mess2 = intent.getStringExtra("cont");
        var1.setText(mess2);
        var2.setText(mess);



        bouton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                try {

                    Contact contact = new Contact(var1.getText().toString(),Long.parseLong(var2.getText().toString()));
                    base.addContact(contact);
                    Intent i = new Intent(Cc.this, RefreshStyle.class);  //your class
                    startActivity(i);
                    finish();


                } catch (Exception e) {


                }
            }
        });





    }

}
