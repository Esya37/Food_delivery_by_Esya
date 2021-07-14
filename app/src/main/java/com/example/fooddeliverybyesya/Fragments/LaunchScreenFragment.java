package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliverybyesya.Models.Categories;
import com.example.fooddeliverybyesya.Models.Category;
import com.example.fooddeliverybyesya.Models.SearchResult;
import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

//TODO Проверить подключение к интернету для избежания бесконечной загрузки
        model.getCategories().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if(!categories.isEmpty()){
                    navController.navigate(R.id.action_launchScreenFragment_to_firstScreenFragment);
                }
            }
        });
//        model.getSearchResult("hello").observe(getViewLifecycleOwner(), new Observer<List<SearchResult>>() {
//            @Override
//            public void onChanged(List<SearchResult> searchResults) {
//                if(searchResults==null){
//                    Log.d("searchResultTag", "Ничего не найдено");
//                    return;
//                }
//                for(SearchResult searchResult : searchResults){
//                    Log.d("searchResultTag", searchResult.getStrMeal());
//                }
//            }
//        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_launch_screen, container, false);
    }

    NavController navController;
    ExecutorService executorService;
    private MainActivityViewModel model;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

}