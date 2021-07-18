package com.example.fooddeliverybyesya.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliverybyesya.Services.DeliveryService;
import com.example.fooddeliverybyesya.Services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryRepository {

    private final DeliveryService deliveryService;
    private Call<Categories> callCategory;
    private Call<SearchResults> callSearchResult;

    private final MutableLiveData<List<Category>> categoryList;
    private final MutableLiveData<List<SearchResult>> searchResultList;

    public DeliveryRepository() {
        deliveryService = RetrofitService.getDeliveryService();
        categoryList = new MutableLiveData<>();
        searchResultList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategories() {
        callCategory = deliveryService.getCategories();
        callCategory.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    categoryList.setValue(response.body().getCategoryList());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                t.printStackTrace();
                Log.d("someTag", "something wrong ((");
            }
        });

        return categoryList;
    }

    public MutableLiveData<List<SearchResult>> getSearchResult(String query) {

        callSearchResult = deliveryService.getSearchResult(query);
        callSearchResult.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if (response.isSuccessful()) {
                    searchResultList.setValue(response.body().getSearchResultList());
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                t.printStackTrace();
                Log.d("someTag", "something wrong ((");
            }
        });

        return searchResultList;
    }
}
