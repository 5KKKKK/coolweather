package com.example.yanhoor.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.yanhoor.coolweather.service.AutoUpdateService;

/**
 * Created by yanhoor on 2016/1/4.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent i=new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
