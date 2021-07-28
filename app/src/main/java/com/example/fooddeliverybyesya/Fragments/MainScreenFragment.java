package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.NavigationUI;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

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

    View inflatedView;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    TempScreenOrdersFragment tempScreenOrdersFragment;
    TempScreenHistoryFragment tempScreenHistoryFragment;
    TempScreenProfileFragment tempScreenProfileFragment;
    NavController navController;
    NavController navControllerMain;
    MainActivityViewModel model;
    FragmentContainerView fragmentContainerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_main_screen, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        bottomNavigationView = inflatedView.findViewById(R.id.bottomNavigationView);
       // fragmentManager = getActivity().getSupportFragmentManager();

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_selected);
       // homeFragment = HomeFragment.newInstance();
       // navController.navigate(R.id.homeFragment);
       // fragmentManager.beginTransaction().add(R.id.fragmentContainerView2, homeFragment).commit();

        model.getSelectedMenuItem().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
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
            }
        });





        model.isUserClickOnSearchView().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    model.isUserClickOnSearchView().removeObserver(this);
                    model.setUserClickOnSearchView(false);
                    navControllerMain.navigate(R.id.action_mainScreenFragment_to_searchScreenFragment);
//                    Map<View, String> map = new HashMap<>();
//                    map.put(homeFragment.searchView, homeFragment.searchView.getTransitionName());
//
//                    navController.navigate(R.id.action_mainScreenFragment_to_searchScreenFragment,
//                            null,
//                            null,
//                            new FragmentNavigator.Extras.Builder().addSharedElements(map).build());
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
      //  navControllerMain = Navigation.findNavController(view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homeFragment:
                       // homeFragment = HomeFragment.newInstance();
                       // fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, homeFragment).commit();
                       // navController.navigate(R.id.homeFragment);
                        model.setSelectedMenuItem(0);
                        break;
                    case R.id.tempScreenOrdersFragment:
                       // tempScreenOrdersFragment = TempScreenOrdersFragment.newInstance();
                       // fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenOrdersFragment).commit();
                       // navController.navigate(R.id.tempScreenOrdersFragment);
                        model.setSelectedMenuItem(1);
                        break;
                    case R.id.tempScreenProfileFragment:
                       // tempScreenHistoryFragment = TempScreenHistoryFragment.newInstance();
                       // fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenHistoryFragment).commit();
                       // navController.navigate(R.id.tempScreenProfileFragment);
                        model.setSelectedMenuItem(2);
                        break;
                    case R.id.tempScreenHistoryFragment:
                      //  tempScreenProfileFragment = TempScreenProfileFragment.newInstance();
                       // fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenProfileFragment).commit();
                       // navController.navigate(R.id.tempScreenHistoryFragment);
                        model.setSelectedMenuItem(3);
                        break;
                }
                //NavigationUI.onNavDestinationSelected(item, navController);
                return NavigationUI.onNavDestinationSelected(item, navController);  //return true;
            }
        });
    }

}