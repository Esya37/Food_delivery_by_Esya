package com.example.fooddeliverybyesya.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliverybyesya.models.Category;
import com.example.fooddeliverybyesya.models.DeliveryRepository;
import com.example.fooddeliverybyesya.models.SearchResult;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final DeliveryRepository deliveryRepository;
    private MutableLiveData<List<Category>> categoryList;
    private MutableLiveData<List<SearchResult>> searchResultList;
    private final MutableLiveData<Integer> selectedMenuItem;
    private final MutableLiveData<Boolean> UserClickOnSearchView;

    public MainActivityViewModel() {
        deliveryRepository = new DeliveryRepository();
        selectedMenuItem = new MutableLiveData<>(0);
        UserClickOnSearchView = new MutableLiveData<>(false);
        categoryList = new MutableLiveData<>();
        searchResultList = new MutableLiveData<>();
    }

    public LiveData<Boolean> isUserClickOnSearchView() {
        return UserClickOnSearchView;
    }

    public void setUserClickOnSearchView(boolean userClickOnSearchView) {
        this.UserClickOnSearchView.setValue(userClickOnSearchView);
    }

    public LiveData<Integer> getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public void setSelectedMenuItem(int selectedMenuItem) {
        this.selectedMenuItem.setValue(selectedMenuItem);
    }

    public LiveData<List<Category>> getCategories(){
        return deliveryRepository.getCategories(categoryList);
    }

    public LiveData<List<SearchResult>> getSearchResult(String query){
        return searchResultList = deliveryRepository.getSearchResult(query, searchResultList);
    }

}
