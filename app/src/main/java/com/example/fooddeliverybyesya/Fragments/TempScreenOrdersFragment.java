package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;

public class TempScreenOrdersFragment extends Fragment {

    public TempScreenOrdersFragment() {
        // Required empty public constructor
    }

    public static TempScreenOrdersFragment newInstance() {
        return new TempScreenOrdersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View inflatedView;
    Button returnTempScreenOrdersButton;
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    MainActivityViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_temp_screen_orders, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        returnTempScreenOrdersButton = inflatedView.findViewById(R.id.returnTempScreenOrdersButton);
        returnTempScreenOrdersButton.setOnClickListener(new View.OnClickListener() {
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