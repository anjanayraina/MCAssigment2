package com.example.mcassigment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.time.OffsetTime;

public class Fragment1  extends Fragment {
    View view;
    ItemViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        Button enter = view.findViewById(R.id.button2);
        TimePicker time = view.findViewById(R.id.timePicker);
        time.setIs24HourView(true);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int minutes = time.getMinute();
                int hours = time.getHour();
                int totalTime =0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    OffsetTime offset = OffsetTime.now();
                    totalTime = (hours  - offset.getHour())*60*60 + (minutes - offset.getMinute())*60;
                    if(totalTime < 0)totalTime =0;
                }
                viewModel.setData("SetTime,"+totalTime);
            }
        });
        return view ;
    }
}
