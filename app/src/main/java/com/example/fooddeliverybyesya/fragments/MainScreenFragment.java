package com.example.fooddeliverybyesya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.view_models.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenFragment extends Fragment {

    public MainScreenFragment() {
        // Required empty public constructor
    }

    public static MainScreenFragment newInstance() {
        return new MainScreenFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
    }

    private View inflatedView;
    private BottomNavigationView bottomNavigationView;

    private NavController navController;
    private NavController navControllerMain;
    private MainActivityViewModel model;
    private FragmentContainerView fragmentContainerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_main_screen, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        bottomNavigationView = inflatedView.findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_selected);


        model.getSelectedMenuItem().observe(getViewLifecycleOwner(), integer -> {
            if ((integer == 0) && (bottomNavigationView.getSelectedItemId() != R.id.homeFragment)) {
                bottomNavigationView.setSelectedItemId(R.id.homeFragment);
                return;
            }

            bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home);
            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_heart);
            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_user);
            bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.ic_sharp_history);

            switch (integer) {
                case 0:
                    bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_selected);
                    break;
                case 1:
                    bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_heart_selected);
                    break;
                case 2:
                    bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_user_selected);
                    break;
                case 3:
                    bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.ic_sharp_history_selected);
                    break;
            }
        });





        model.isUserClickOnSearchView().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    model.isUserClickOnSearchView().removeObserver(this);
                    model.setUserClickOnSearchView(false);
                    navControllerMain.navigate(R.id.action_mainScreenFragment_to_searchScreenFragment);
                }
            }
        });

        fragmentContainerView = inflatedView.findViewById(R.id.fragmentContainerView2);
        fragmentContainerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                fragmentContainerView.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView2);
        navControllerMain = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.homeFragment:
                    model.setSelectedMenuItem(0);
                    break;
                case R.id.tempScreenOrdersFragment:
                    model.setSelectedMenuItem(1);
                    break;
                case R.id.tempScreenProfileFragment:
                    model.setSelectedMenuItem(2);
                    break;
                case R.id.tempScreenHistoryFragment:
                    model.setSelectedMenuItem(3);
                    break;
            }
            return NavigationUI.onNavDestinationSelected(item, navController);
        });
    }

}