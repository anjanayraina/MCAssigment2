package com.example.mcassigment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                Log.d("Calling" , "CAll incoming");
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Log.d("Calling" , "CAll Asnwered ");
                // handle call answered
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                // handle call ended
                Log.d("Calling" , "CAll Ended");
            }
        }
    }
}
