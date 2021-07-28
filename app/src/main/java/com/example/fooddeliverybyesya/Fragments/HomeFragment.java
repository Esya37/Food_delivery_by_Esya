package com.example.fooddeliverybyesya.Fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;
import com.example.fooddeliverybyesya.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

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

    View inflatedView;
    RecyclerView recyclerView;
    MainActivityViewModel model;
    TextView categoryNameTextView;
    ImageView categoryImageView;
    SearchView searchView;
    ViewPager2 viewPager;
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    searchView.clearFocus();
                    model.setUserClickOnSearchView(true);
                }
            }
        });

        viewPager = inflatedView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), getLifecycle(), model.getCategories().getValue().size()));

        tabLayout = inflatedView.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(TabLayout.Tab tab, int position) {
                        tab.setText(model.getCategories().getValue().get(position).getStrCategory());
                    }
                }).attach();

        // Inflate the layout for this fragment
        return inflatedView;
    }

    private void onItemClick(View view, int position) {

        categoryNameTextView.setText(model.getCategories().getValue().get(position).getStrCategory());
        Picasso.with(categoryImageView.getContext()).load(model.getCategories().getValue().get(position).getStrCategoryThumb()).resize((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.5), 0).into(categoryImageView);

    }

}