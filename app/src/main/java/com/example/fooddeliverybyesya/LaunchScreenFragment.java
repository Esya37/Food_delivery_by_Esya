package com.example.fooddeliverybyesya;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LaunchScreenFragment extends Fragment {

    public LaunchScreenFragment() {
        // Required empty public constructor
    }

    public static LaunchScreenFragment newInstance() {
        return new LaunchScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_screen, container, false);
    }
}