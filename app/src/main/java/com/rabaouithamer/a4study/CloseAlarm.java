package com.rabaouithamer.a4study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CloseAlarm extends AppCompatActivity {
    TextView tvNum1;
    TextView tvNum2;
    TextView edtResult;
    TextView tvOperator;
    TextView tvEqual;
    Button btnOff;
    Random rand = new Random();
    String operatorList[] = {"+","-","*"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_alarm);

        tvNum1 = (TextView) findViewById(R.id.tvNum1);
        tvNum2 = (TextView) findViewById(R.id.tvNum2);
        tvOperator = (TextView) findViewById(R.id.tvOperator);
        tvEqual = (TextView) findViewById(R.id.tvEqual);
        edtResult = (EditText) findViewById(R.id.edtResult);

        btnOff = (Button) findViewById(R.id.btnOff);

        int a = 1+ rand.nextInt(30);
        int b = 1+ rand.nextInt(30);
        tvNum1.setText(String.valueOf(a));
        tvNum2.setText(String.valueOf(b));


        tvOperator.setText(operatorList[rand.nextInt(operatorList.length)]);


        //final int result = Integer.parseInt(tvNum1.getText().toString().concat(tvOperator.getText().toString().concat(tvNum2.getText().toString())));
      /* btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtResult.getText().toString().equals(String.valueOf(resolveEquation()))){
                    Intent intent_alarm = new Intent(getApplicationContext(),AlarmTab.class);
                    startActivity(intent_alarm);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Reponse incorrect",
                            Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }

    /*public int resolveEquation(){
        int result = 0;
        if(tvOperator.getText().toString().equals("+")){
             result = Integer.parseInt(tvNum1.getText().toString()) + Integer.parseInt(tvNum2.getText().toString());

        }
        else if(tvOperator.getText().toString().equals("-")){
            result = Integer.parseInt(tvNum1.getText().toString()) - Integer.parseInt(tvNum2.getText().toString());
        }
        else if(tvOperator.getText().toString().equals("*")){
            result = Integer.parseInt(tvNum1.getText().toString()) * Integer.parseInt(tvNum2.getText().toString());
        }
        return result;
    }*/
}
