package com.example.fooddeliverybyesya;

import android.os.Bundle;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_screen, container, false);
    }

    NavController navController;
    ExecutorService executorService;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        executorService = Executors.newSingleThreadExecutor();
        Callable<Boolean> task = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Thread.sleep(3000);        //Типо что-то загружается
                getActivity().runOnUiThread(new Runnable() {   //При навигации из другого потока осуществлялось перемещение, но не менялся destination
                    @Override
                    public void run() {
                        navController.navigate(R.id.action_launchScreenFragment_to_firstScreenFragment);
                    }
                });

                return null;
            }
        };
        executorService.submit(task);
        executorService.shutdown();


    }
}