package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliverybyesya.R;

public class TempScreenFragment extends Fragment {

    public TempScreenFragment() {
        // Required empty public constructor
    }

    public static TempScreenFragment newInstance() {
        return new TempScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temp_screen, container, false);
    }
}