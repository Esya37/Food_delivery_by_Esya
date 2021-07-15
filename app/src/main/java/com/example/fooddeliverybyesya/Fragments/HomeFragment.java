package com.example.fooddeliverybyesya.Fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.RecyclerViewAdapter;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;
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
    RecyclerViewAdapter recyclerViewAdapter;
    MainActivityViewModel model;
    TextView categoryNameTextView;
    ImageView categoryImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_home, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        recyclerView = (RecyclerView)inflatedView.findViewById(R.id.categoriesRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflatedView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new RecyclerViewAdapter(inflatedView.getContext(), model.getCategories().getValue());
        recyclerViewAdapter.setClickListener(this::onItemClick);
        recyclerView.setAdapter(recyclerViewAdapter);

        categoryNameTextView = inflatedView.findViewById(R.id.categoryNameTextView);
        categoryNameTextView.setText(model.getCategories().getValue().get(0).getStrCategory());
        categoryImageView = inflatedView.findViewById(R.id.categoryImageView);
        Picasso.with(categoryImageView.getContext()).load(model.getCategories().getValue().get(0).getStrCategoryThumb()).resize((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.5), 0).into(categoryImageView);

        SearchView searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    searchView.clearFocus();
                    model.setUserClickOnSearchView(true);
                }
            }
        });
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                model.setUserClickOnSearchView(true);
//            }
//        });

        // Inflate the layout for this fragment
        return inflatedView;
    }

    private void onItemClick(View view, int position) {

        categoryNameTextView.setText(model.getCategories().getValue().get(position).getStrCategory());
        Picasso.with(categoryImageView.getContext()).load(model.getCategories().getValue().get(position).getStrCategoryThumb()).resize((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.5), 0).into(categoryImageView);

    }

}