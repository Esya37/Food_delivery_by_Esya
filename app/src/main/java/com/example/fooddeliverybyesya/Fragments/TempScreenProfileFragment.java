package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;

public class TempScreenProfileFragment extends Fragment {

    public TempScreenProfileFragment() {
        // Required empty public constructor
    }

    public static TempScreenProfileFragment newInstance() {
        TempScreenProfileFragment fragment = new TempScreenProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View inflatedView;
    Button returnTempScreenProfileButton;
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    MainActivityViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_temp_screen_profile, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        returnTempScreenProfileButton = inflatedView.findViewById(R.id.returnTempScreenProfileButton);
        returnTempScreenProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.setSelectedMenuItem(0);
                homeFragment = HomeFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, homeFragment).commit();

            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }
}