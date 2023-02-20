package com.example.mcassigment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class Fragment3  extends Fragment {
    View view;
    ItemViewModel viewModel;
    Button stopBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View finalView = inflater.inflate(R.layout.fragment3, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment3, null);
        stopBtn = finalView.findViewById(R.id.button);

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setData("StopTimer");
            }
        });
        return finalView;
    }
}
