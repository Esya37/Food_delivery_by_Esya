package com.example.fooddeliverybyesya;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
    }

    View inflatedView;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    HomeFragment homeFragment;
    TempScreenFragment tempScreenFragment;
    NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_main_screen, container, false);
        bottomNavigationView = inflatedView.findViewById(R.id.bottomNavigationView);
        fragmentManager = getActivity().getSupportFragmentManager();

        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_selected);
        homeFragment = HomeFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.fragmentContainerView2, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_heart);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_user);
                bottomNavigationView.getMenu().getItem(3).setIcon(R.drawable.ic_sharp_history);
                switch (item.getItemId()){
                    case R.id.home:
                        homeFragment = HomeFragment.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, homeFragment).commit();
                        item.setIcon(R.drawable.ic_home_selected);
                        break;
                    case R.id.heart:
                        tempScreenFragment = TempScreenFragment.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenFragment).commit();
                        item.setIcon(R.drawable.ic_heart_selected);
                        break;
                    case R.id.user:
                        tempScreenFragment = TempScreenFragment.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenFragment).commit();
                        item.setIcon(R.drawable.ic_user_selected);
                        break;
                    case R.id.history:
                        tempScreenFragment = TempScreenFragment.newInstance();
                        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, tempScreenFragment).commit();
                        item.setIcon(R.drawable.ic_sharp_history_selected);
                        break;
                }
                return true;
            }
        });



        // Inflate the layout for this fragment
        return inflatedView;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        NavigationUI.onNavDestinationSelected(item, navController);
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        //NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}