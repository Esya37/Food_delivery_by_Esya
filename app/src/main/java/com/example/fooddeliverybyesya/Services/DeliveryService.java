package com.example.fooddeliverybyesya.Services;

import com.example.fooddeliverybyesya.Models.Categories;
import com.example.fooddeliverybyesya.Models.Category;
import com.example.fooddeliverybyesya.Models.SearchResult;
import com.example.fooddeliverybyesya.Models.SearchResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeliveryService {

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("search.php")
    Call<SearchResults> getSearchResult(@Query("s") String query);

}
