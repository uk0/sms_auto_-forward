package com.firshme.sms_auto_forward;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), "加载配置文件", duration);
        toast.show();

        IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        localIntentFilter.setPriority(2147483647);
        SmsRecevier localMessageReceiver = new SmsRecevier(getConfigForwardPhone());
        Log.v("dimos", "MyService");
        registerReceiver(localMessageReceiver, localIntentFilter);
    }
    String getConfigForwardPhone(){
        try {
            FileInputStream inStream = openFileInput("config.dat");
            byte data[]=new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
            String str=new String(data);
            int duration = Toast.LENGTH_SHORT;
            String sendPhone = str.split(",")[0];
            Toast toast = Toast.makeText(getApplicationContext(), "Send Phone :"+sendPhone, duration);
            toast.show();
            return sendPhone;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
