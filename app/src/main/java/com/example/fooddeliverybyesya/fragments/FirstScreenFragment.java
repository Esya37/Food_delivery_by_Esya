package com.example.fooddeliverybyesya.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fooddeliverybyesya.R;


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

    private View inflatedView;
    private Button startButton;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_first_screen, container, false);
        startButton = inflatedView.findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> navController.navigate(R.id.action_firstScreenFragment_to_mainScreenFragment));

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

}