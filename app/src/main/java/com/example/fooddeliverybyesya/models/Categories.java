package com.example.fooddeliverybyesya.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("categories")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

}
