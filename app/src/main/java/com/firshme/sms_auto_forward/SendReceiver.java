package com.firshme.sms_auto_forward;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SendReceiver extends BroadcastReceiver {
    public static final String ACTION = "action.send.sms";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION.equals(action)) {
            int resultCode = getResultCode();
            if (resultCode == Activity.RESULT_OK) {
                // 发送成功
                System.out.println("发送成功！");
            } else {
                // 发送失败
                System.out.println("发送失败！");
            }
        }
    }
}