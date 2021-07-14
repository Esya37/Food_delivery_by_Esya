package com.example.fooddeliverybyesya.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {

    @SerializedName("meals")
    private List<SearchResult> searchResultList;

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }
}
