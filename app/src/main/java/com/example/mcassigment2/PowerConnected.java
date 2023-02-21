package com.example.mcassigment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PowerConnected extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "The device is charging", Toast.LENGTH_SHORT).show();
            Log.d("Charging" , "Battery Charging");
        } else {
            intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
            Toast.makeText(context, "The device is not charging", Toast.LENGTH_SHORT).show();
            Log.d("Charging" , "Battery Charging");
        }
    }
}
