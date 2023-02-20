package com.example.mcassigment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public ItemViewModel viewModel;
    CountDownTimer timer;
    Long time;

    boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedItems().observe(this , item->{
//            Toast.makeText(getApplicationContext() , item , Toast.LENGTH_LONG).show();
            String temp[] = item.split(",");
            if(temp[0].equals("SetTime")){
//                Toast.makeText(getApplicationContext() , "Time Set to "+temp[1] + " seconds" , Toast.LENGTH_LONG).show();
//                time =Long.getLong(temp[1]);
                time = Long.parseLong(temp[1]);
//                Toast.makeText(getApplicationContext() , ""+time , Toast.LENGTH_LONG).show();

            }

            else if(temp[0].equals("StartTime")){
//                Toast.makeText(getApplicationContext(), "Timer Started" , Toast.LENGTH_LONG).show();

                startTimer();
            }

            else if(temp[0].equals("StopTimer")){
                stopTimer();
                Toast.makeText(getApplicationContext() , "Timer Stopped" , Toast.LENGTH_LONG).show();
            }

        });


    }

    public void startTimer(){

        timer = new CountDownTimer(time  ,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext() , "Timer Ended" , Toast.LENGTH_LONG).show();
            }
        }.start();
        running = true;
    }

    public void stopTimer(){
        timer.cancel();
        running =false;
    }


}