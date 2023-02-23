package com.example.mcassigment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public ItemViewModel viewModel;
    CountDownTimer timer;

    CountDownTimer songTimer;
    Long time;

    boolean running;

    MediaPlayer player;

    String musicURL  = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

    PowerConnected reciever =new PowerConnected();;

    CallReciver callComing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(reciever, ifilter);
        player = new MediaPlayer();
        player.setAudioAttributes(new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build());
        try {
            player.setDataSource(musicURL);
            player.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                batteryLow();
            }
        };
        registerReceiver(broadcastReceiver , new IntentFilter(Intent.ACTION_BATTERY_LOW));

        BroadcastReceiver chargingBattery = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = context.registerReceiver(null, ifilter);
                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
                if(isCharging){
                    Toast.makeText(getApplicationContext() , "Battery Charging" , Toast.LENGTH_LONG).show();
                    Log.d("BatteryCharging" , "Battery is Charging");
                }
            }
        };

        registerReceiver(chargingBattery , new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        callComing = new CallReciver();
        IntentFilter intentFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        BroadcastReceiver phoneCall  = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext() , "Phone Call received !!" , Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(callComing , intentFilter);


        viewModel.getSelectedItems().observe(this , item->{

            String temp[] = item.split(",");
            if(temp[0].equals("SetTime")){

                time = Long.parseLong(temp[1]);
                Toast.makeText(getApplicationContext() , "Timer Set " , Toast.LENGTH_LONG).show();
                Log.d("SetTimer", "Timer Set !!");

            }

            else if(temp[0].equals("StartTime")){
                Toast.makeText(getApplicationContext(), "Timer Started" , Toast.LENGTH_LONG).show();
                Log.d("StartTimer" , "Timer Started");
                startTimer();
            }

            else if(temp[0].equals("StopTimer")){
                stopTimer();
                if(running)Toast.makeText(getApplicationContext() , "Timer Stopped By User " , Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getApplicationContext() , "Song Stoped" , Toast.LENGTH_LONG).show();
                }
            }

        });


    }

    public void startTimer(){

        timer = new CountDownTimer(time*1000  ,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext() , "Timer Ended" , Toast.LENGTH_LONG).show();
                Log.d("TimerEnded" , "The Timer has Ended");
                startSong();
            }
        }.start();
        running = true;
    }

    public void stopTimer(){
        timer.cancel();
        player.stop();
        running =false;
        Log.d("TimerEnded" , "The Timer has been Stopped");
    }

    public void startSong(){
        CountDownTimer cntr_aCounter = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {

                player.start();
            }

            public void onFinish() {
                //code fire after finish
                player.stop();
            }
        };cntr_aCounter.start();
//        player.start();
    }
    public void batteryLow(){
        Toast.makeText(getApplicationContext() , "Battery Low , Timer and Song Stopped" , Toast.LENGTH_LONG).show();
        stopTimer();
    }

    @Override
    public void onStart(){
        super.onStart();


    }

    @Override
    public void onStop(){
        super.onStop();
        unregisterReceiver(reciever);

    }


}