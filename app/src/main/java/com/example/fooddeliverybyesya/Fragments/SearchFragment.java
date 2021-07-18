package com.example.fooddeliverybyesya.Fragments;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import androidx.transition.TransitionInflater;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.search_view_transition));
        setEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.slide_bottom));

    }

    View inflatedView;
    SearchView searchView;
    MainActivityViewModel model;
    RecyclerView recyclerView;
    RecyclerViewAdapterSearchResult recyclerViewAdapter;
    TextView countSearchResultsTextView;
    List<SearchResult> searchResultsList;
    Button returnSearchScreenButton;

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

        searchView = inflatedView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!hasConnection(getContext())) {
                    Toast.makeText(getContext(), "Check your Internet connection and try again", Toast.LENGTH_SHORT).show();
                }
                model.getSearchResult(query).observe(getViewLifecycleOwner(), new Observer<List<SearchResult>>() {
                    @Override
                    public void onChanged(List<SearchResult> searchResults) {
                        if (searchResults != null) {
                            countSearchResultsTextView.setText("Found " + searchResults.size() + " results");
                            searchResultsList.clear();
                            searchResultsList.addAll(searchResults);
                        } else {
                            countSearchResultsTextView.setText("Found 0 results");
                            searchResultsList.clear();
                        }
                        recyclerViewAdapter.notifyDataSetChanged();

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        returnSearchScreenButton = inflatedView.findViewById(R.id.returnSearchScreenButton);
        returnSearchScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        searchView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                searchView.getViewTreeObserver().removeOnPreDrawListener(this::onPreDraw);
                startPostponedEnterTransition();
                return true;
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //startPostponedEnterTransition();
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