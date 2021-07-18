package com.example.fooddeliverybyesya.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliverybyesya.Models.Category;
import com.example.fooddeliverybyesya.Models.DeliveryRepository;
import com.example.fooddeliverybyesya.Models.SearchResult;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final DeliveryRepository deliveryRepository;
    private LiveData<List<Category>> categoryList;
    private LiveData<List<SearchResult>> searchResultList;
    private final MutableLiveData<Integer> selectedMenuItem;
    private final MutableLiveData<Boolean> UserClickOnSearchView;

    public MainActivityViewModel() {
        deliveryRepository = new DeliveryRepository();
        selectedMenuItem = new MutableLiveData<>(0);
        UserClickOnSearchView = new MutableLiveData<>(false);
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
        categoryList = deliveryRepository.getCategories();
        return categoryList;
    }

    public LiveData<List<SearchResult>> getSearchResult(String query){
        searchResultList = deliveryRepository.getSearchResult(query);
        return searchResultList;
    }

}
