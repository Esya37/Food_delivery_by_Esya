package com.example.fooddeliverybyesya.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverybyesya.R;
import com.example.fooddeliverybyesya.RecyclerViewAdapterSearchResult;
import com.example.fooddeliverybyesya.models.SearchResult;
import com.example.fooddeliverybyesya.view_models.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();

    }

    private View inflatedView;
    private SearchView searchView;
    private MainActivityViewModel model;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterSearchResult recyclerViewAdapter;
    private TextView countSearchResultsTextView;
    private List<SearchResult> searchResultsList;
    private Button returnSearchScreenButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_search_screen, container, false);

        model = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        countSearchResultsTextView = inflatedView.findViewById(R.id.countSearchResultsTextView);

        searchResultsList = new ArrayList<>();

        recyclerView = inflatedView.findViewById(R.id.searchResultRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(inflatedView.getContext(), 2));
        recyclerViewAdapter = new RecyclerViewAdapterSearchResult(inflatedView.getContext(), searchResultsList);
        recyclerView.setAdapter(recyclerViewAdapter);

        searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!hasConnection(getContext())) {
                    Toast.makeText(getContext(), "Check your Internet connection and try again", Toast.LENGTH_SHORT).show();
                }
                model.getSearchResult(query).observe(getViewLifecycleOwner(), searchResults -> {
                    if (searchResults != null) {
                        countSearchResultsTextView.setText("Found " + searchResults.size() + " results");
                        searchResultsList.clear();
                        searchResultsList.addAll(searchResults);
                    } else {
                        countSearchResultsTextView.setText("Found 0 results");
                        searchResultsList.clear();
                    }
                    recyclerViewAdapter.notifyDataSetChanged();

                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        returnSearchScreenButton = inflatedView.findViewById(R.id.returnSearchScreenButton);
        returnSearchScreenButton.setOnClickListener(v -> {
            searchView.clearFocus();
            getActivity().onBackPressed();
        });



        searchView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                searchView.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onPause() {
        searchView.clearFocus();
        super.onPause();
    }
}