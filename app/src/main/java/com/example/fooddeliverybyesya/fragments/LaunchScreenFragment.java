package com.example.fooddeliverybyesya.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.view_models.MainActivityViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        if(!hasConnection(getContext())){
            Toast.makeText(getContext(),"Check your Internet connection and try again", Toast.LENGTH_SHORT).show();
        }
        model.getCategories().observe(getViewLifecycleOwner(), categories -> {
            if(!categories.isEmpty()){
                navController.navigate(R.id.action_launchScreenFragment_to_firstScreenFragment);
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_screen, container, false);
    }

    private NavController navController;
    private MainActivityViewModel model;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        return wifiInfo != null && wifiInfo.isConnected();
    }

}