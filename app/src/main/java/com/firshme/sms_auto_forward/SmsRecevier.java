package com.firshme.sms_auto_forward;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;

public class SmsRecevier extends BroadcastReceiver {
    public SmsRecevier() {
        super();
        Log.v("dimos", "SmsRecevier create");
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String dString = SmsHelper.getSmsBody(intent);
        String address = SmsHelper.getSmsAddress(intent);
        Log.i("dimos", dString+","+address);
        //阻止广播继续传递，如果该receiver比系统的级别高，
        //那么系统就不会收到短信通知了

        SmsManager smsManager = SmsManager.getDefault();
        Intent intent2 = new Intent();
        intent.setAction(SendReceiver.ACTION);
        ArrayList<String> divideMessage = smsManager.divideMessage("短信来自  ： \n" +
                " " + address + " \r\n "+dString+" \r\n");
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 1, intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);
        ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
        sentIntents.add(sentIntent);
        try {
            smsManager.sendMultipartTextMessage("需要转发的手机号", null,
                    divideMessage, sentIntents, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        abortBroadcast();

    }
}