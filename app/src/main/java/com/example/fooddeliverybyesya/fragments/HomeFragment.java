package com.example.fooddeliverybyesya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewPagerAdapter;
import com.example.fooddeliverybyesya.view_models.MainActivityViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View inflatedView;
    private MainActivityViewModel model;
    private SearchView searchView;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                searchView.clearFocus();
                model.setUserClickOnSearchView(true);
            }
        });

        viewPager = inflatedView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), getLifecycle(), model.getCategories().getValue().size()));

        tabLayout = inflatedView.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(model.getCategories().getValue().get(position).getStrCategory())).attach();

        // Inflate the layout for this fragment
        return inflatedView;
    }

}