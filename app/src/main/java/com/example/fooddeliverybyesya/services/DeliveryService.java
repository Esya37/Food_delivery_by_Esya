package com.example.fooddeliverybyesya.services;

import com.example.fooddeliverybyesya.models.Categories;
import com.example.fooddeliverybyesya.models.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeliveryService {

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("search.php")
    Call<SearchResults> getSearchResult(@Query("s") String query);

}
