package com.example.fooddeliverybyesya.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliverybyesya.Models.Categories;
import com.example.fooddeliverybyesya.Models.Category;
import com.example.fooddeliverybyesya.Models.DeliveryRepository;
import com.example.fooddeliverybyesya.Models.SearchResult;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final DeliveryRepository deliveryRepository;
    private LiveData<List<Category>> categoryList;
    private LiveData<List<SearchResult>> searchResultList;

    public MainActivityViewModel() {
        deliveryRepository = new DeliveryRepository();
    }

    public LiveData<List<Category>> getCategories(){
        categoryList = deliveryRepository.getCategories();
        return categoryList;
    }

    public LiveData<List<SearchResult>> getSearchResult(String query){
        searchResultList = deliveryRepository.getSearchResult(query);
        return searchResultList;
    }

}
