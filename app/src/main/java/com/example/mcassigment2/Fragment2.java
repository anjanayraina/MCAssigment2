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
import androidx.lifecycle.ViewModelProvider;

public class Fragment2 extends Fragment {
    Button startBtn;
    View view;
    ItemViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View finalView = inflater.inflate(R.layout.fragment_fragment2, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_fragment2, null);
        startBtn = finalView.findViewById(R.id.button);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               viewModel.setData("StartTime");
            }
        });
        return finalView  ;
    }
}
