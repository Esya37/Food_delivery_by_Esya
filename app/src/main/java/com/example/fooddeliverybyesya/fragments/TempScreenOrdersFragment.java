package com.example.fooddeliverybyesya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.view_models.MainActivityViewModel;

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

    private View inflatedView;
    private Button returnTempScreenOrdersButton;
    private MainActivityViewModel model;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_temp_screen_orders, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        returnTempScreenOrdersButton = inflatedView.findViewById(R.id.returnTempScreenOrdersButton);
        returnTempScreenOrdersButton.setOnClickListener(v -> {

            model.setSelectedMenuItem(0);
            navController.navigate(R.id.homeFragment);

        });

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView2);
    }
}