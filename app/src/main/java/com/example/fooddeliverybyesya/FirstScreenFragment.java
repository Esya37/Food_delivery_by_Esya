package com.example.fooddeliverybyesya;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FirstScreenFragment extends Fragment {

    public FirstScreenFragment() {
        // Required empty public constructor
    }

    public static FirstScreenFragment newInstance() {
        return new FirstScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_screen, container, false);
    }
}