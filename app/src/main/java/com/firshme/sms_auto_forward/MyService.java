package com.firshme.sms_auto_forward;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        localIntentFilter.setPriority(2147483647);
        SmsRecevier localMessageReceiver = new SmsRecevier();
        Log.v("dimos", "MyService");
        registerReceiver(localMessageReceiver, localIntentFilter);
    }
}
