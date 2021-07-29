package com.example.fooddeliverybyesya.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {

    @SerializedName("meals")
    private List<SearchResult> searchResultList;

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

}
