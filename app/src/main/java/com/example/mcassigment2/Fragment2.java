package com.example.mcassigment2;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    Button startBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View finalView = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_fragment2, null);
        startBtn = root.findViewById(R.id.button);
        Log.d("StartButton", "the button is : " + startBtn.getText());
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast toast =  Toast.makeText(getContext() , "Start Button Clicked" , Toast.LENGTH_LONG);
               toast.setGravity(Gravity.TOP|Gravity.CENTER, 0 , 0);
               toast.show();
            }
        });
        return finalView  ;
    }
}
