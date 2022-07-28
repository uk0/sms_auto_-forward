package com.firshme.sms_auto_forward;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBrocast extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("dimos", "MyBrocast");
        if (intent.getAction().equals(ACTION)) {
            Intent service = new Intent(context, MyService.class);
            context.startService(service);
        }
    }
}