package com.example.mcassigment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public ItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        viewModel.getSelectedItems().observe(this , item->{
            String temp[] = item.split(",");
            if(temp[0].equals("SetTime")){
                Toast.makeText(getApplicationContext() , "Time Set to "+temp[1] + " seconds" , Toast.LENGTH_LONG).show();
            }

        });


    }
}