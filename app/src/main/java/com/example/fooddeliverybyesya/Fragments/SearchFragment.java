package com.example.fooddeliverybyesya.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.fooddeliverybyesya.Models.SearchResult;
import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.RecyclerViewAdapter;
import com.example.fooddeliverybyesya.RecyclerViewAdapterSearchResult;
import com.example.fooddeliverybyesya.ViewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View inflatedView;
    SearchView searchView;
    MainActivityViewModel model;
    RecyclerView recyclerView;
    RecyclerViewAdapterSearchResult recyclerViewAdapter;
    TextView countSearchResultsTextView;
    List<SearchResult> searchResultsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_search_screen, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        countSearchResultsTextView = inflatedView.findViewById(R.id.countSearchResultsTextView);

        searchResultsList = new ArrayList<>();

        recyclerView = (RecyclerView) inflatedView.findViewById(R.id.searchResultRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(inflatedView.getContext(), 2));
        recyclerViewAdapter = new RecyclerViewAdapterSearchResult(inflatedView.getContext(), searchResultsList);
        recyclerView.setAdapter(recyclerViewAdapter);

        //TODO Сделать список результатов красивым

        searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                model.getSearchResult(query).observe(getViewLifecycleOwner(), new Observer<List<SearchResult>>() {
                    @Override
                    public void onChanged(List<SearchResult> searchResults) {
                        if (searchResults != null) {
                            countSearchResultsTextView.setText("Found " + searchResults.size() + " results");
                            searchResultsList.clear();
                            searchResultsList.addAll(searchResults);
                            recyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            countSearchResultsTextView.setText("Found 0 results");
                            searchResultsList.clear();
                            recyclerViewAdapter.notifyDataSetChanged();
                        }

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        // Inflate the layout for this fragment
        return inflatedView;
    }

}