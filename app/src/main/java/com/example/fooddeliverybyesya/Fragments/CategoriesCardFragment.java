package com.example.fooddeliverybyesya.Fragments;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;
import com.squareup.picasso.Picasso;

public class CategoriesCardFragment extends Fragment {

    private static final String ARG_PARAM1 = "positionOfCategory";

    private int positionOfCategory;

    public CategoriesCardFragment() {
        // Required empty public constructor
    }

    public static CategoriesCardFragment newInstance(int positionOfCategory) {
        CategoriesCardFragment fragment = new CategoriesCardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, positionOfCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            positionOfCategory = getArguments().getInt(ARG_PARAM1);
        }
    }

    View inflatedView;
    MainActivityViewModel model;
    TextView categoryNameTextView;
    ImageView categoryImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_categories_card, container, false);
        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        categoryNameTextView = inflatedView.findViewById(R.id.categoryNameTextView);
        categoryImageView = inflatedView.findViewById(R.id.categoryImageView);

        categoryNameTextView.setText(model.getCategories().getValue().get(positionOfCategory).getStrCategory());
        Picasso.with(categoryImageView.getContext()).load(model.getCategories().getValue().get(positionOfCategory).getStrCategoryThumb()).resize((int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.5), 0).into(categoryImageView);

        // Inflate the layout for this fragment
        return inflatedView;
    }
}