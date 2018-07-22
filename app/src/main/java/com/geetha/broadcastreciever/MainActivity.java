package com.geetha.broadcastreciever;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linear;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       linear = findViewById(R.id.linear);

        Button btnFetch = findViewById(R.id.btn_fetch);
        btnFetch.setOnClickListener(this);
        db = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        List<SmsModel> smsData =  db.getAllData();
        for(int i = 0; i<smsData.size();i++){
            TextView tv = new TextView(MainActivity.this);
            tv.setText(smsData.get(i).toString());
            linear.addView(tv);
        }
    }
}
