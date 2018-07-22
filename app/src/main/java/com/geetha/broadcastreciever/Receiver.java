package com.geetha.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Rdl on 15-07-2018.
 */

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Received SMS", Toast.LENGTH_SHORT).show();

        //get the Intent Bundle
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;

        //get the SMS messages from bundle
        Object[] items = (Object[]) bundle.get("pdus");
        msgs = new SmsMessage[items.length];
        for (int i = 0; i < msgs.length; i++) {

            byte[] item = (byte[])items[i];
            SmsMessage sms = SmsMessage.createFromPdu(item);

            //get sms data
            String from = sms.getOriginatingAddress();
            String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
            String msg = sms.getMessageBody().toString();

            if(from.equals("+919900130858")) {
                Toast.makeText(context, "Received msg from Geetha", Toast.LENGTH_SHORT).show();

                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                }else{
                    Log.i("Receiver","Vibrates");


                    //deprecated in API 26
                    v.vibrate(500);
                }
            }

            SmsModel textMsg = new SmsModel(from, date, msg);
            persistSmsData(textMsg, context);
        }
    }

    private void persistSmsData(SmsModel textMsg, Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        helper.insertData(textMsg);
    }
}
